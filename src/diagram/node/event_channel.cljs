(ns diagram.node.event-channel
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["react-flow-renderer" :refer (Handle)]

            [diagram.node.utils :as u]
            [diagram.card.flippable :as f]
            [diagram.card.entity :as entity]
            [diagram.card.chart :as chart]

            [chart.pie-chart :as pie-chart]))


(defn node [data]
  (let [d     (js->clj data)
        label (get-in d ["data" "label"])
        image (get-in d ["data" "image"])
        id    (get d "id")]

    (reagent/as-element
      [:div#event-channel {:style u/node-style-card}
       [f/flippable {}
        [entity/card {:background       u/event-color
                      :background-color u/event-color}
         image label]
        [chart/card {:background u/event-color}
         [pie-chart/pie-chart {:background u/event-color}]]]

       [:> Handle {:id    (str id "-out") :type "source" :position "top"
                   :style u/handle-style}]])))