(ns globe-cljs.cell.layer-support
  (:require [taoensso.timbre :as log]

            [re-frame.core :as re-frame]
            [re-frame.db :as rdb]
            [cljs-time.core :as cljs-time]
            [cljs-time.coerce :as coerce]

            [globe-cljs.subs :as subs]

            [globe-cljs.cell.util :as cell]
            [globe.worldwind.location :as location]
            [globe.worldwind.position :as position]
            [globe.worldwind.geographic-text :as geo-text]
            [globe.worldwind.text-attributes :as text-attr]
            [globe.worldwind.layer.renderable :as rl]
            [globe.worldwind.surface.polygon :as poly]))


(def sensor-color-pallet [[:green "rgba(0, 128, 0, .3)" [0.0 0.5 0.0 0.3]] ; "abi-3"
                          [:blue "rgba(0, 0, 255, .3)" [0.0 0. 1.0 0.3]] ; "abi-1"
                          [:orange "rgba(255, 165, 0, .3)" [1.0 0.65 0.0 0.3]] ; "avhhr-6"
                          [:grey "rgba(128, 128, 128, .3)" [0.5 0.5 0.5 0.3]] ; "viirs-5"
                          [:cornflowerblue "rgba(100, 149, 237, .3)" [0.4 0.58 0.93 0.3]] ; "abi-meso-11"
                          [:darkcyan "rgba(0, 139, 139, .3)" [1.0 0.55 0.55 0.3]] ; "abi-meso-4"
                          [:goldenrod "rgba(218, 165, 32, .3)" [0.84 0.65 0.13 0.3]] ; "abi-meso-10"
                          [:khaki "rgba(240, 230, 140, .3)" [0.94 0.90 0.55 0.3]] ; "abi-meso-2"
                          [:deepskyblue "rgba(0, 191, 255, .3)" [1.0 0.0 1.0 0.3]]
                          [:navy "rgba(0, 0, 128, .3)" [0.0 0.0 0.5 0.3]]
                          [:cyan "rgba(0, 255, 255, .3)" [0.0 1.0 1.0 0.9]]
                          [:darkred "rgba(139, 0, 0, .3)" [0.55 0.0 0.0 0.3]]
                          [:darkseagreen "rgba(143, 188, 143, .3)" [0.55 0.74 0.56 0.3]]
                          [:darkviolet "rgba(148, 0, 211, .3)" [0.58 0 0.83 0.3]]
                          [:forestgreen "rgba(34, 139, 34, .3)" [1.0 0.71 0.76 0.9]]
                          [:orchid "rgba(218, 112, 214, .3)" [0.84 0.44 0.84 0.3]]
                          [:plum "rgba(221, 160, 221, .3)" [0.87 0.63 0.87 0.9]]
                          [:tomato "rgba(255, 99, 71, .3)" [1.0 0.39 0.28 0.3]]
                          [:orangered "rgba(255, 69, 0, .3)" [1.0 0.27 0.0 0.3]]])


(def starting-date-time (cljs-time/date-time 2022 1 15 12 0 0 0))


(defn get-sensor-colors [sensors]
  (if (seq sensors)
    (zipmap sensors (cycle sensor-color-pallet))
    []))


(defn- make-polygon [colors cell]
  (let [[pos sensor] (first cell)
        cell-text (str pos)
        boundaries (cell/cell-boundaries pos)
        locations  (->> boundaries
                     (map (fn [location]
                            (location/location location)))
                     (into-array))
        center (position/position (get cell/cell-centers pos))
        [color-name _ ww-color] (get colors sensor)
        layer-name (str cell-text "-" sensor)]

    ;(log/info "make-polygon" pos sensor color-name ww-color)
    {layer-name
     (rl/renderable-layer layer-name
       [(poly/polygon locations {:color ww-color})])}))
        ;(geo-text/geographic-text center sensor
        ;  (text-attr/text-attributes))])}))


(re-frame/reg-sub
  ::subs/layers

  (fn [[_ id _] _]
    (re-frame/subscribe [::subs/current-cells id]))

  (fn [cells [_ id colors]]
    (->> cells
      (map #(make-polygon colors %))
      (into {})
      (merge (get-in @rdb/app-db [:widgets id :layers])))))


(comment
  (def children [(globe-cljsglobe.worldwind.surface.polygon/polygon [0 0] {:color [255 0 0 1]})
                 (globe-cljsglobe.worldwind.surface.polygon/polygon [0 1] {:color [0 255 0 1]})
                 (globe-cljsglobe.worldwind.surface.polygon/polygon [1 0] {:color [0 0 255 1]})])

  @(re-frame/subscribe [::subs/current-cells])
  @(re-frame/subscribe [::subs/current-cells])
  @(re-frame/subscribe [::subs/layers])


  (def cell @(re-frame/subscribe [::subs/current-cells]))

  (merge {(str cell)
          (rl/createLayer (str cell)
            [(globe-cljsglobe.worldwind.surface.polygon/polygon cell {:color [128 128 0 0.3]})])}
    (:layers @re-frame.db/app-db))

  ())


; how do we destructure the key and value of the cell data?
(comment
  (def cell {[0 0] "sensor-1"})
  (def cell-data [{[0 0] "sensor-1"}
                  {[0 0] "sensor-2"}])

  (let [[k v] (first cell)]
    {:k k :v v})

  (map (fn [c]
         (let [[k v] (first c)]
           {:k k :v v}))
    cell-data)


  ())
