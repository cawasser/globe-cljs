(ns chart.line-chart
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["recharts" :refer (LineChart Line CartesianGrid XAxis YAxis)]

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
   :mark "line"
   :encoding {:x {:field "time"
                  :type "ordinal"}
              :y {:aggregate "sum"
                  :field "quantity"
                  :type "quantitative"}
              :color {:field "item"
                      :type "nominal"
                      :legend {:direction "horizontal" :orient "bottom"
                               :columns 2}}}})


(defn line-chart []
  [:div#line-chart-component {:style {:width 250 :height 300
                                      :background u/default-background}}
   [oz.core/vega-lite (data)]])




     ;[:> LineChart {:width "450px" :height "300" :data default-data}
     ; [:> Line {:type "monotone" :dataKey "uv" :stroke "#8884d8"}]]]))
     ; [:> CartesianGrid {:stroke "#ccc"}]
     ; [:> XAxis {:dataKey "name"}]
     ; [:> YAxis]]]))

