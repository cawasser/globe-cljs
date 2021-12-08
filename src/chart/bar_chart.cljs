(ns chart.bar-chart
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            [oz.core :as oz]
            [diagram.node.utils :as u]))


(def default-data [{:name "Page A" :uv 400 :pv 2400 :amt 2400}
                   {:name "Page B" :uv 1400 :pv 400 :amt 2400}
                   {:name "Page C" :uv 900 :pv 1400 :amt 2400}
                   {:name "Page D" :uv 400 :pv 400 :amt 2400}
                   {:name "Page E" :uv 100 :pv 1400 :amt 2400}])


(defn- play-data [& names]
  (for [n names
        i (range 8)]
    {:time i :item n :quantity (+ (Math/pow (* i (count n)) 0.8) (rand-int (count n)))}))


(defn data []
  {:width 250
   :height 300
   :autosize {:type "fit"
              :contains "padding"}
   :background u/default-background
   :data {:values (play-data "munchkin" "witch" "dog" "lion" "tiger" "bear")}
   :mark "bar"
   :encoding {:x {:field "time"
                  :type "ordinal"}
              :y {:aggregate "sum"
                  :field "quantity"
                  :type "quantitative"}
              :color {:field "item"
                      :type "nominal"
                      :legend {:direction "horizontal" :orient "bottom"
                               :columns 2}}}})


(defn bar-chart []
  (reagent/as-element
    [:div#line-chart-component
     [oz.core/vega-lite (data)]]))


