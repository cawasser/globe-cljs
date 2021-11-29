(ns globe-cljs.globe
  (:require ["worldwindjs" :as WorldWind]
            [reagent.core :as reagent]
            [reagent.dom :as rdom]
            [re-frame.core :as rf]
            [taoensso.timbre :as log]
            [clojure.set :as set]
            [globe-cljs.events :as events]

            [globe-cljs.layer.layer :as l]
            [globe-cljs.layer.renderable :as rl]

            [globe-cljs.layer.blue-marble :as bm]
            [globe-cljs.layer.compass :as compass]
            [globe-cljs.layer.controls :as controls]
            [globe-cljs.layer.tesselation :as tess]
            [globe-cljs.surface.polygon :as poly]))


(def last-this (atom {}))


(def DEFAULT_BACKGROUND_COLOR "rgb(36,74,101)")

(def projections ["3D",
                  "Equirectangular",
                  "Mercator",
                  "North Polar",
                  "South Polar",
                  "North UPS",
                  "South UPS",
                  "North Gnomonic",
                  "South Gnomonic"])


(defn- change-projection [this new-projection]
  (reset! last-this this)

  (log/info "change-projection" new-projection
    "//// roundGlobe" (.-roundGlobe this)
    "//// flatGlobe " (.-flatGlobe this))

  (if (= "3D" new-projection)
    (do
      (log/info "changing to roundGlobe")
      (if (not (.-roundGlobe this))
        (set! (.-roundGlobe this) (WorldWind/Globe. (WorldWind/EarthElevationModel.))))

      ; Replace the flat globe
      (if (not= (.. this -wwd -globe) (.-roundGlobe this))
        (do
          (log/info "setting the roundGlobe" (.-roundGlobe this))
          (set! (.. this -wwd -globe) (.-roundGlobe this))
          (.redraw (.-wwd this)))))

    (do
      (log/info "changing to flatGlobe")

      (if (not (.-flatGlobe this))
        (set! (.-flatGlobe this) (WorldWind/Globe2D.)))

      (set! (.. this -flatGlobe -projection)
        (condp = new-projection
          "Equirectangular" (WorldWind/ProjectionEquirectangular.)
          "Mercator" (WorldWind/ProjectionMercator.)
          "North Polar" (WorldWind/ProjectionPolarEquidistant. "North")
          "South Polar" (WorldWind/ProjectionPolarEquidistant. "South")
          "North UPS" (WorldWind/ProjectionUPS. "North")
          "South UPS" (WorldWind/ProjectionUPS. "South")
          "North Gnomonic" (WorldWind/ProjectionGnomonic. "North")
          "South Gnomonic" (WorldWind/ProjectionGnomonic. "South")))

      (if (not= (.. this -wwd -globe) (.-flatGlobe this))
        (do
          (log/info "setting the flatGlobe" (.-flatGlobe this))
          (set! (.. this -wwd -globe) (.-flatGlobe this))
          (.redraw (.-wwd this)))))))


(defn- update-children [this new-children old-children]
  (let [new-keys (set (keys new-children))
        old-keys (set (keys old-children))
        added    (set/difference new-keys old-keys)
        removed  (set/difference old-keys new-keys)]

    ; remove old stuff
    (if removed
      (doall
        ;(log/info "removing" removed)
        (map #(l/removeLayer this %) removed)))

    ; add new stuff
    (if added
      (doall
        ;(log/info "adding" added)
        (for [[idx child] (map-indexed vector added)]
          (do
            ;(log/info "adding" idx child)
            (l/addLayer this idx [child (get new-children child)])))))))


(defn- component-did-mount [dom-node state this]
  (log/info "component-did-mount" @state)

  (let [node (rdom/dom-node this)]
    ;; This will trigger a re-render of the component.
    (reset! dom-node node))

  (let [canvasId (.-id @dom-node)
        props    (reagent/props this)]

    ;Create the WorldWindow using the ID of the canvas
    (set! (.-wwd this) (WorldWind/WorldWindow. canvasId))
    (swap! state assoc :wwd (.-wwd this))

    ; Apply projection support
    (set! (.-roundGlobe this) (.-globe (.-wwd this)))

    (log/info "mount projection" (:projection props))

    (if (:projection props)
      (change-projection this (:projection props)))

    (doall
      (for [[idx child] (map-indexed vector (first (reagent/children this)))]
        (do
          ;(log/info "adding layer" idx child)
          (l/addLayer this idx child))))

    ; add the controls layer
    (l/addLayer this -1 ["Controls" (controls/createLayer this "Controls")])))


(defn- component-did-update [dom-node state this old-argv]
  (let [[_ new-props new-children] (reagent/argv this)
        [old-id old-props old-children] old-argv]

    (log/info "component-did-update old-children" old-children
      "//// old-props" old-props
      "//// new-children" new-children
      "//// new-props" new-props)

    (log/info "projection"
      (:projection old-props)
      (:projection new-props))

    (if (not= (:projection old-props) (:projection new-props))
      (change-projection this (:projection new-props)))

    (update-children this new-children old-children)))


(defn globe [props & children]
  ;(log/info "globe" props children)

  (let [state    (atom {:children children})
        dom-node (reagent/atom nil)]

    (reagent/create-class
      {:display-name         (:id props)

       :constructor          (fn [this props]
                               ;(log/info "constructor" (reagent/props this) (reagent/children this))
                               (swap! state assoc
                                 :nextIndex 0
                                 :wwd ()
                                 :canvasId (or (.-canvasId props) (str "canvas_" (js/Date.now)))
                                 :id (or (.-canvasId props) (str "canvas_" (js/Date.now)))
                                 :isValid false
                                 :isDropArmed false
                                 :projection (or (.-projection props) (nth projections 0))
                                 :layers (reagent/children this)))

       :component-did-mount  (partial component-did-mount dom-node state)

       :component-did-update (partial component-did-update dom-node state)

       :reagent-render
       (fn [props & children]
         @dom-node
         (let [cursor          (if (:isDropArmed @state) "crosshair" "default")
               backgroundColor (or (:backgroundColor @state) DEFAULT_BACKGROUND_COLOR)]

           [:canvas (merge props {:id        (:canvasId @state)
                                  :nextIndex (:nextIndex @state)})
            "Your browser does not support HTML5 Canvas."]))})))


; try updating the app-db and seeing if the globe updates
(comment
  (rf/dispatch-sync [::events/add-base-layer
                     "Compass" (compass/createLayer "Compass")])
  (rf/dispatch-sync [::events/remove-base-layer "Compass"])

  (rf/dispatch-sync [::events/add-base-layer
                     "Tesselation" (tess/createLayer "Tesselation")])
  (rf/dispatch-sync [::events/remove-base-layer "Tesselation"])

  (rf/dispatch-sync [::events/add-base-layer
                     "Blue Marble" (bm/createLayer "Blue Marble")])
  (rf/dispatch-sync [::events/remove-base-layer "Blue Marble"])

  ())


; now we can try a more complicated layer: renderable with polygons!
(comment
  (do
    (require '[globe-cljs.layer.renderable :as rl])
    (require '[globe-cljs.surface.polygon :as poly])
    (def children [(poly/createPolygon [0 0] {:color [255 0 0 1]})
                   (poly/createPolygon [0 1] {:color [0 255 0 1]})
                   (poly/createPolygon [1 0] {:color [0 0 255 1]})])
    (def layer (rl/createLayer "polygons" children)))

  (count (.-renderables layer))

  (rf/dispatch-sync [::events/add-layer
                     "polygons" (rl/createLayer
                                  "polygons"
                                  [(poly/createPolygon [0 0] {:color [255 0 0 1]})
                                   (poly/createPolygon [8 0] {:color [255 0 0 1]})
                                   (poly/createPolygon [0 1] {:color [0 255 0 1]})
                                   (poly/createPolygon [1 0] {:color [0 0 255 1]})])])
  (rf/dispatch-sync [::events/remove-layer "polygons"])
  @re-frame.db/app-db

  ; now some other polygons
  (rf/dispatch-sync [::events/add-layer
                     "poly-2" (rl/createLayer "poly-2"
                                [(poly/createPolygon [5 5]
                                   {:color [128 128 0 1]})])])
  (rf/dispatch-sync [:globe-cljs.events/remove-layer "poly-2"])

  ())



; change projections
(comment
  (def new-projection "Mercator")

  @last-this

  (if (not (.-flatGlobe @last-this))
    (set! (.-flatGlobe @last-this) (WorldWind/Globe2D.)))

  (set! (.. @last-this -flatGlobe -projection)
    (WorldWind/ProjectionEquirectangular.))

  ())