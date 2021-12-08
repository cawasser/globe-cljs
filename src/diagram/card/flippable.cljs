(ns diagram.card.flippable
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["react-ui-cards" :refer (FlippingCard FlippingCardBack FlippingCardFront)]))


(defn flippable [props front back]
  [:> FlippingCard (merge {:style {:margin 0}} props)
   [:> FlippingCardFront
    front]
   [:> FlippingCardBack
    back]])