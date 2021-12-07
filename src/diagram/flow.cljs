(ns diagram.flow
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]
            ["react-flow-renderer" :default ReactFlow]
            [globe-cljs.sensor-data :as sd]

            [re-frame.core :as re-frame]
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


(defn diagram [elements]
  (let [is-active (reagent/atom false)
        source    (reagent/atom "")
        target    (reagent/atom "")]
    [:div {:style {:width "100%" :height "100%"}}

     [animated-or-not-popover is-active source target]

     [:> ReactFlow {:elements  @elements
                    :nodeTypes (clj->js {"globe" globe/globe-node})
                    :onConnect (partial onConnect is-active source target)}]]))



;;;;;;;;;;;;;;;;;;;;;;;;
; RICH COMMENTS

; play with changing the elements in the diagram
(comment
  (re-frame/dispatch [::events/add-element {:id       "5"
                                            :el-type  :node
                                            :data     {:label "A new node!"}
                                            :position {:x 300 :y 125}}])

  (re-frame/dispatch [::events/update-element {:id       "5"
                                               :el-type  :node
                                               :data     {:label "My label has changed!!!"}
                                               :position {:x 300 :y 125}}])


  (re-frame/dispatch [::events/update-element {:id       "5100"
                                               :type     "globe"
                                               :el-type  :node
                                               :data     {:sensor "viirs-5"}
                                               :position {:x 300 :y 125}}])

  (re-frame/dispatch [::events/update-element {:id       "5200"
                                               :type     "globe"
                                               :el-type  :node
                                               :data     {:sensor "avhhr-6"}
                                               :position {:x 300 :y 275}}])

  (re-frame/dispatch [::events/update-element {:id       "5300"
                                               :type     "globe"
                                               :el-type  :node
                                               :data     {:sensor "abi-meso-4"}
                                               :position {:x 700 :y 125}}])


  (re-frame/dispatch [::events/remove-element "5"])
  (re-frame/dispatch [::events/remove-element "5100"])
  (re-frame/dispatch [::events/remove-element "5200"])
  (re-frame/dispatch [::events/remove-element "5300"])


  ()


  ())