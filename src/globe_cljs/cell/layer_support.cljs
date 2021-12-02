(ns globe-cljs.cell.layer-support
  (:require [taoensso.timbre :as log]

            [re-frame.core :as re-frame]
            [re-frame.db :as rdb]
            [globe-cljs.subs :as subs]

            [globe-cljs.cell.util :as cell]
            [globe.worldwind.location :as location]
            [globe.worldwind.position :as position]
            [globe.worldwind.geographic-text :as geo-text]
            [globe.worldwind.text-attributes :as text-attr]
            [globe.worldwind.layer.renderable :as rl]
            [globe.worldwind.surface.polygon :as poly]))


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
       [(poly/polygon locations {:color ww-color})
        (geo-text/geographic-text center sensor
          (text-attr/text-attributes))])}))


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
