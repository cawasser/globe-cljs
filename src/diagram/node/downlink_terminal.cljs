(ns diagram.node.downlink-terminal
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["react-flow-renderer" :refer (Handle)]
            ["react-ui-cards" :refer (FlippingCard FlippingCardBack FlippingCardFront)]

            [chart.line-chart :as c]
            [diagram.card.entity :as entity]
            [diagram.card.chart :as chart]

            [diagram.node.utils :as u]))


(defn downlink-terminal [data]
  (let [d (js->clj data)
        label (get-in d ["data" "label"])
        id (get d "id")]

    (reagent/as-element
      [:div#platform-card {:style (merge u/node-style-card {:margin 0})}
       [:> FlippingCard {:style {:margin 0}}
        [:> FlippingCardFront
         [entity/card {} "/images/icons/downlink-terminal.png" label]]
        [:> FlippingCardBack
         [chart/card {} [c/line-chart]]]]

       [:> Handle {:id    (str id "-out") :type "source" :position "right"
                   :style u/handle-style}]
       [:> Handle {:id    (str id "-in") :type "target" :position "left"
                   :style u/handle-style}]])))

