(ns diagram.flow
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            ["react-flow-renderer" :refer (MiniMap Controls) :default ReactFlow]

            [globe-cljs.events :as events]
            [diagram.node.globe :as globe]))


(defn- animated-or-not-popover [is-active source target]
  (fn []
    [:div#animated.modal (if @is-active {:class "is-active"})
     [:div.modal-background]
     [:div.modal-card
      [:header.modal-card-head
       [:p.modal-card-title "Animate the link?"]]
      [:section.modal-card-body]
      [:footer.modal-card-foot
       [:button.button.is-info {:on-click #(do
                                             (re-frame/dispatch [::events/add-element {:id       (str @source "-" @target)
                                                                                       :el-type  :edge
                                                                                       :source   @source
                                                                                       :target   @target
                                                                                       :animated true}])
                                             (reset! is-active false))}
        "Yes"]
       [:button.button {:on-click #(do
                                     (re-frame/dispatch [::events/add-element {:id      (str @source "-" @target)
                                                                               :el-type :edge
                                                                               :source  @source
                                                                               :target  @target}])
                                     (reset! is-active false))}
        "No"]]]]))


(defn- onConnect [is-active source target params]
  (let [p (js->clj params)]

    (reset! source (get p "source"))
    (reset! target (get p "target"))

    (log/info "onConnect" @source @target p)

    (reset! is-active true)))


(defn diagram [elements node-types edge-types]
  (let [is-active (reagent/atom false)
        source    (reagent/atom "")
        target    (reagent/atom "")]

    ;(log/info "diagram" @elements node-types)

    [:div {:style {:width "100%" :height "100%"}}

     [animated-or-not-popover is-active source target]

     [:> ReactFlow {:elements  @elements
                    :nodeTypes (clj->js node-types)
                    :edgeTypes (clj->js edge-types)
                    :onConnect (partial onConnect is-active source target)}
      [:> MiniMap]
      [:> Controls]]]))



