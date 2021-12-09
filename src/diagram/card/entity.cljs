(ns diagram.card.entity
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]
            [diagram.node.utils :as u]))


(defn card [props image text]
  (reagent/as-element
    [:div#entity-card {:style (merge u/node-style-square props)}
     [:img {:style (merge u/image-style
                     {:background-color u/default-background}
                     props)
            :src   image}]
     [:div.subtitle.is-3 {:style u/label-style} text]]))



(comment

  (do
    (def image "dummy")
    (def props {:background-color u/view-color
                :background       u/view-color}))


  [:div {:style (merge u/node-style-square props)}]

  [:img {:style (merge u/image-style
                  {:background-color u/default-background}
                  props)
         :src   image}]

  ())