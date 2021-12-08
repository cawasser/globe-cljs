(ns diagram.node.platform
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["react-flow-renderer" :refer (Handle)]
            ["react-ui-cards" :refer (FlippingCard FlippingCardBack FlippingCardFront)]

            [diagram.node.utils :as u]
            [chart.pie-chart :as c]
            [diagram.card.entity :as entity]
            [diagram.card.chart :as chart]))


(defn platform [data]
  (let [d     (js->clj data)
        label (get-in d ["data" "label"])
        id    (get d "id")]

    (reagent/as-element
      [:div#platform-card {:style u/node-style-card}
       [:> FlippingCard {:style {:margin 0}}
        [:> FlippingCardFront
         [entity/card {} "/images/icons/Weather-Satellite-PNG-Clipart.png" label]]
        [:> FlippingCardBack
         [chart/card {} [c/pie-chart]]]]

       [:> Handle {:id    (str id "-out") :type "source" :position "right"
                   :style u/handle-style}]
       [:> Handle {:id    (str id "-in") :type "target" :position "left"
                   :style u/handle-style}]])))
