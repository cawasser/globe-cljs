(ns diagram.node.platform
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["react-flow-renderer" :refer (Handle)]

            [diagram.node.utils :as u]
            [chart.pie-chart :as c]
            [diagram.card.flippable :as f]
            [diagram.card.entity :as entity]
            [diagram.card.chart :as chart]))


(defn node [data]
  (let [d     (js->clj data)
        label (get-in d ["data" "label"])
        image (get-in d ["data" "image"])
        id    (get d "id")]

    (reagent/as-element
      [:div#platform-card {:style u/node-style-card}
       [f/flippable {}
        [entity/card {} image label]
        [chart/card {} [c/pie-chart {}]]]

       [:> Handle {:id    (str id "-out") :type "source" :position "right"
                   :style u/handle-style}]
       [:> Handle {:id    (str id "-in") :type "target" :position "left"
                   :style u/handle-style}]])))
