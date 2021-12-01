(ns globe-cljs.renderableLayer
  (:require ["worldwindjs" :as WorldWind]
            [reagent.core :as reagent]
            [reagent.dom :as rdom]
            [taoensso.timbre :as log]))


(defn- component-did-mount [dom-node this]
  (let [node (rdom/dom-node this)]
    ;; This will trigger a re-render of the component.
    (reset! dom-node node))

  ;(log/info "renderableLayer/component-did-mount")

  (rdom/render
    [:div (reagent/props this) "renderableLayer"]
    @dom-node))


(defn- component-did-update [dom-node this old-argv])


(defn renderableLayer [props & children]
  (let [state (atom {})
        dom-node (reagent/atom nil)]

    (reagent/create-class
      {:display-name         (:id props)

       :component-did-mount  (partial component-did-mount dom-node)

       :component-did-update (partial component-did-update dom-node)

       :reagent-render
       (fn [args]
         @dom-node
         [:div])})))