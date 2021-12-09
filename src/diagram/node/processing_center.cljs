(ns diagram.node.processing-center
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["react-flow-renderer" :refer (Handle)]

            [chart.bar-chart :as c]
            [diagram.card.flippable :as f]
            [diagram.card.entity :as entity]
            [diagram.card.chart :as chart]

            [diagram.node.utils :as u]))


(defn node [data]
  (let [d (js->clj data)
        label (get-in d ["data" "label"])
        image (get-in d ["data" "image"])
        id (get d "id")]

    (reagent/as-element
      [:div#platform-card {:style u/node-style-card}
       [f/flippable {:style {:margin 0}}
        [entity/card {} image label]
        [chart/card {} [c/bar-chart {}]]]

       [:> Handle {:id    (str id "-in") :type "target" :position "left"
                   :style u/handle-style}]])))
