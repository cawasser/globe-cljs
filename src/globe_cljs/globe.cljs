(ns globe-cljs.globe
  (:require ["worldwindjs" :as WorldWind]
            [reagent.core :as reagent]
            [reagent.dom :as rdom]
            [re-frame.core :as rf]
            [taoensso.timbre :as log]
            [clojure.set :as set]

            [globe-cljs.layer.layer :as l]

            [globe-cljs.layer.compass :as compass]))


(def last-this (atom {}))


(def DEFAULT_BACKGROUND_COLOR "rgb(36,74,101)")


(defn- update-children [this new-children old-children]
  (let [new-keys (set (keys new-children))
        old-keys (set (keys old-children))
        added (set/difference new-keys old-keys)
        removed (set/difference old-keys new-keys)]

    ; remove old stuff
    (if removed
      (doall
        (map #(l/removeLayer this %) removed)))

    ; add new stuff
    (if added
      (doall
        (map #(l/addLayer this [% (get new-children %)]) added)))))


(defn- component-did-mount [dom-node state this]
  (log/info "component-did-mount" @state)

  (let [node (rdom/dom-node this)]
    ;; This will trigger a re-render of the component.
    (reset! dom-node node))

  (let [canvasId (.-id @dom-node)
        props (reagent/props this)]

    ;Create the WorldWindow using the ID of the canvas
    (set! (.-wwd this) (WorldWind/WorldWindow. canvasId))
    (swap! state assoc :wwd (.-wwd this))

    ; Apply projection support
    (set! (.-roundGlobe this) (.-globe (.-wwd this)))

    (if (.-projection (reagent/props this))
      (set! (.-changeProjection this) (.-projection props)))

    (doall
      (log/info "mapping layers" (first (reagent/children this)))
      (map #(l/addLayer this %)
        (first (reagent/children this))))))


(defn- component-did-update [dom-node state this old-argv]
  (let [[_ new-props new-children] (reagent/argv this)
        [old-id old-props old-children] old-argv]

    (log/info "component-did-update old-children" old-children
      "//// old-argv" old-argv
      "//// new-children" new-children
      "//// new-argv" new-props)

    (update-children this new-children old-children)))


(defn globe [props & children]
  (log/info "globe" props children)

  (let [state (atom {:children children})
        dom-node (reagent/atom nil)]

    (reagent/create-class
      {:display-name         (:id props)

       :constructor (fn [this props]
                      (log/info "constructor" (reagent/props this) (reagent/children this))
                      (swap! state assoc
                        :nextIndex 0
                        :wwd ()
                        :canvasId (or (.-canvasId props) (str "canvas_" (js/Date.now)))
                        :id (.-canvasId props)
                        :isValid false
                        :isDropArmed false
                        :layers (reagent/children this)))

       :component-did-mount  (partial component-did-mount dom-node state)

       :component-did-update (partial component-did-update dom-node state)

       :reagent-render
       (fn [props & children]
         @dom-node
         (let [cursor (if (:isDropArmed @state) "crosshair" "default")
               backgroundColor (or (:backgroundColor @state) DEFAULT_BACKGROUND_COLOR)]

           [:canvas (merge props {:id (:canvasId @state)})
            "Your browser does not support HTML5 Canvas."]))})))


; try updating the app-db and seeing if the globe updates
(comment
  (require '[globe-cljs.layer.compass :as compass])
  (rf/dispatch-sync [:globe-cljs.events/add-layer
                     "Compass" (compass/createLayer "Compass")])
  (rf/dispatch-sync [:globe-cljs.events/remove-layer
                     "Compass"])

  ())