(ns diagram.flow
  (:require ["react-flow-renderer" :default ReactFlow]
            [globe-cljs.sensor-data :as sd]

            [re-frame.core :as re-frame]
            [globe-cljs.events :as events]))



(defn diagram [elements]
  [:> ReactFlow {:elements @elements}])



;;;;;;;;;;;;;;;;;;;;;;;;
; RICH COMMENTS

; play with changing the elements in the diagram
(comment
  (re-frame/dispatch [::events/add-element {:id       "5"
                                            :type     "input" ; input node
                                            :data     {:label "A new node!"}
                                            :position {:x 300 :y 125}}])

  (re-frame/dispatch [::events/update-element {:id       "5"
                                               :type     "input" ; input node
                                               :data     {:label "My label has changed!!!"}
                                               :position {:x 300 :y 125}}])

  (re-frame/dispatch [::events/remove-element "5"])


  ()


  ())