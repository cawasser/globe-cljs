(ns globe-cljs.diagram-component
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [globe-cljs.events :as events]
    [globe-cljs.subs :as subs]
    [taoensso.timbre :as log]

    [diagram.flow :as flow]
    [diagram.node.globe :as globe]
    [diagram.node.platform :as platform]
    [diagram.node.downlink-terminal :as downlink-terminal]
    [diagram.node.processing-center :as processing-center]))


(defn diagram []
  (reagent/with-let [elements (re-frame/subscribe [::subs/diagram-elements])]

    [:div#diagram {:style {:width        "50%" :height "500px"
                           :padding      "20px" :border-width "3px"
                           :border-style :solid :border-color :black}}
     [flow/diagram elements
      {"globe" globe/globe-node
       "platform" platform/platform
       "downlink-terminal" downlink-terminal/downlink-terminal
       "processing-center" processing-center/processing-center}
      {}]]))


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


  (re-frame/dispatch [::events/update-element {:id       "5100"
                                               :type     "globe"
                                               :draggable false
                                               :el-type  :node
                                               :data     {:sensor "viirs-5"}
                                               :position {:x 450 :y 125}}])
  (re-frame/dispatch [::events/update-element {:id       "5200"
                                               :type     "globe"
                                               :draggable false
                                               :el-type  :node
                                               :data     {:sensor "avhhr-6"}
                                               :position {:x 450 :y 350}}])
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