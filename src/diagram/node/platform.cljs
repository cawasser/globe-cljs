(ns diagram.node.platform
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["react-flow-renderer" :refer (Handle)]
            ["react-ui-cards" :refer (FlippingCard FlippingCardBack FlippingCardFront)]

            [diagram.node.utils :as u]
            [chart.pie-chart :as c]))


(defn platform [data]
  (let [d     (js->clj data)
        label (get-in d ["data" "label"])
        id    (get d "id")]

    (reagent/as-element
      [:div#platform-card {:style u/node-style-card}
       [:> FlippingCard {:style {:margin 0}}
        [:> FlippingCardFront
         [:div {:style u/node-style-square}
          [:img {:style (merge u/image-style {:background-color u/default-background})
                 :src   "/images/icons/Weather-Satellite-PNG-Clipart.png"}]
          [:div.subtitle.is-3 {:style u/label-style} label]]]
        [:> FlippingCardBack
         [:div {:style u/node-style-square}
          [c/pie-chart]]]]

       [:> Handle {:id    (str id "-out") :type "source" :position "right"
                   :style u/handle-style}]
       [:> Handle {:id    (str id "-in") :type "target" :position "left"
                   :style u/handle-style}]])))
