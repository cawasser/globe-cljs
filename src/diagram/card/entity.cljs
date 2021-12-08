(ns diagram.card.entity
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]
            [diagram.node.utils :as u]))


(defn card [props image text]
  (reagent/as-element
    [:div {:style (merge u/node-style-square props)}
     [:img {:style (merge u/image-style {:background-color u/default-background})
            :src   image}]
     [:div.subtitle.is-3 {:style u/label-style} text]]))