(ns diagram.node.downlink-terminal
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["react-flow-renderer" :refer (Handle)]

            [chart.line-chart :as c]
            [diagram.card.entity :as entity]
            [diagram.card.chart :as chart]
            [diagram.card.flippable :as f]

            [chart.line-chart :as line-chart]

            [diagram.node.utils :as u]))


(defn node [data]
  (let [d (js->clj data)
        label (get-in d ["data" "label"])
        image (get-in d ["data" "image"])
        id (get d "id")]

    (reagent/as-element
      [:div#platform-card {:style (merge u/node-style-card {:margin 0})}
       [f/flippable {}
        [entity/card {} image label]
        [chart/card {} [line-chart/line-chart {}]]]

       [:> Handle {:id    (str id "-out") :type "source" :position "right"
                   :style u/handle-style}]
       [:> Handle {:id    (str id "-in") :type "target" :position "left"
                   :style u/handle-style}]])))

