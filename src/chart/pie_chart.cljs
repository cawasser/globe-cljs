(ns chart.pie-chart
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            [oz.core :as oz]
            [diagram.node.utils :as u]))


(defn play-data [num-cats]
  (for [i (range num-cats)]
    {:category i :value (rand-int (* num-cats 10))}))


(defn data []
  {:width 250
   :height 300
   :autosize {:type "fit"
              :contains "padding"}
   :background u/default-background
   :data     {:values (play-data 8)}
   :mark     "arc"
   :encoding {:theta {:field "value", :type "quantitative"},
              :color {:field "category", :type "nominal"
                      :legend {:direction "horizontal" :orient "bottom"
                               :columns 2}}}})


(defn pie-chart []
  [:div#line-chart-component {:style {:width 250 :height 300}}
   [oz.core/vega-lite (data)]])

