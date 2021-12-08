(ns diagram.card.chart
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]
            [diagram.node.utils :as u]))


(defn card [props chart]
  (reagent/as-element
    [:div {:style (merge u/node-style-square props)}
     chart]))