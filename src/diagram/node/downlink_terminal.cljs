(ns diagram.node.downlink-terminal
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["react-flow-renderer" :refer (Handle)]
            ["react-ui-cards" :refer (FlippingCard FlippingCardBack FlippingCardFront)]

            [chart.line-chart :as c]

            [diagram.node.utils :as u]))


(defn downlink-terminal [data]
  (let [d (js->clj data)
        label (get-in d ["data" "label"])
        id (get d "id")]

    (reagent/as-element
      [:div#platform-card {:style u/node-style-card}
       [:> FlippingCard
        [:> FlippingCardFront
         [:div {:style u/node-style-square}
          [:img {:style (merge u/image-style {:background-color u/default-background})
                 :src   "/images/icons/downlink-terminal.png"}]
          [:div u/label-style label]]]
        [:> FlippingCardBack
         [:div {:style u/node-style-square}
          [c/line-chart]]]]

       [:> Handle {:id    (str id "-out") :type "source" :position "right"
                   :style {:borderRadius "true 0 true true"}}]
       [:> Handle {:id    (str id "-in") :type "target" :position "left"
                   :style {:borderRadius "true 0 true true"}}]])))

