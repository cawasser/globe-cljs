(ns diagram.node.processing-center
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["react-flow-renderer" :refer (Handle)]

            [chart.bar-chart :as c]
            [diagram.card.flippable :as f]
            [diagram.card.entity :as entity]
            [diagram.card.chart :as chart]

            [diagram.node.utils :as u]))


(defn processing-center [data]
  (let [d (js->clj data)
        label (get-in d ["data" "label"])
        id (get d "id")]

    (reagent/as-element
      [:div#platform-card {:style u/node-style-card}
       [f/flippable {:style {:margin 0}}
        [entity/card {} "/images/icons/processing-center.png" label]
        [chart/card {} [c/bar-chart]]]

       [:> Handle {:id    (str id "-in") :type "target" :position "left"
                   :style u/handle-style}]])))
