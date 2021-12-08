(ns diagram.card.flippable
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["react-ui-cards" :refer (FlippingCard FlippingCardBack FlippingCardFront)]))


(defn flippable [front back]
  (reagent/as-element
    [:> FlippingCard {:style {:margin 0}}
     [:> FlippingCardFront
      front]
     [:> FlippingCardBack
      back]]))