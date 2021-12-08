(ns diagram.node.flipping-node
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["react-flow-renderer" :refer (Handle)]
            ["react-ui-cards" :refer (FlippingCard FlippingCardBack FlippingCardFront)]

            [diagram.node.utils :as u]))




(defn flipping-node [data]
  (let [d (js->clj data)
        id (get d "id")]

    (reagent/as-element
      [:div
       [:> FlippingCard {:style u/node-style-square}
        [:> FlippingCardBack {:style u/node-style-square}
          [:img {:style u/image-style
                 :src "/images/notched-compass.png"}]]
        [:> FlippingCardFront {:style u/node-style-square}
         [:img {:style u/image-style
                :src "/images/icons/Weather-Satellite-PNG-Clipart.png"}]]]

       [:> Handle {:id (str id "-out") :type "source" :position "right"
                   :style {:borderRadius "true 0 true true"}}]
       [:> Handle {:id (str id "-in") :type "target" :position "left"
                   :style {:borderRadius "true 0 true true"}}]])))
