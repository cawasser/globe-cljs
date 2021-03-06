(ns globe-cljs.flow-diagram
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [globe-cljs.events :as events]
    [globe-cljs.subs :as subs]
    [taoensso.timbre :as log]

    [diagram.flow :as flow]))


(defn diagram [{:keys [id style] :as props} elements node-types edge-types]
  (log/info "diagram" id props elements node-types edge-types)

  [:div.diagram-component {:style (merge {:padding      "10px" :border-width "3px"
                                          :border-style :solid :border-color :black}
                                    style)}
   [flow/diagram id
    elements
    node-types edge-types]])


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;
; RICH COMMENTS
;

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


  (re-frame/dispatch [::events/update-element {:id        "5100"
                                               :type      "globe"
                                               :draggable false
                                               :el-type   :node
                                               :data      {:sensor "viirs-5"}
                                               :position  {:x 450 :y 125}}])
  (re-frame/dispatch [::events/update-element {:id        "5200"
                                               :type      "globe"
                                               :draggable false
                                               :el-type   :node
                                               :data      {:sensor "avhhr-6"}
                                               :position  {:x 450 :y 350}}])
  (re-frame/dispatch [::events/update-element {:id        "5300"
                                               :type      "globe"
                                               :draggable false
                                               :el-type   :node
                                               :data      {:sensor "abi-meso-4"}
                                               :position  {:x 675 :y 125}}])
  (re-frame/dispatch [::events/update-element {:id        "5400"
                                               :type      "globe"
                                               :draggable false
                                               :el-type   :node
                                               :data      {:sensor "abi-meso-11"}
                                               :position  {:x 675 :y 350}}])


  (re-frame/dispatch [::events/remove-element "5"])
  (re-frame/dispatch [::events/remove-element "5100"])
  (re-frame/dispatch [::events/remove-element "5200"])
  (re-frame/dispatch [::events/remove-element "5300"])
  (re-frame/dispatch [::events/remove-element "5400"])

  ())


; FlippingCard from react-ui-cards
(comment
  (re-frame/dispatch [::events/update-element {:id       "flipping-node"
                                               :el-type  :node
                                               ;:draggable false
                                               :type     "flipping-node"
                                               :data     {:label "My label has changed!!!"}
                                               :position (sd/diagram-cell 0 2)}])

  (keys @re-frame.db/app-db)
  (keys (:widgets @re-frame.db/app-db))
  (keys (:aois @re-frame.db/app-db))
  (count (:weather-flow-elements @re-frame.db/app-db))

  (->> (:weather-flow-elements @re-frame.db/app-db)
    (map :id))


  ())


