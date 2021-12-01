(ns globe-cljs.cell.layer-support
  (:require [taoensso.timbre :as log]

            [re-frame.core :as re-frame]
            [re-frame.db :as rdb]
            [globe-cljs.subs :as subs]

            [globe-cljs.cell.util :as cell]
            [globe-cljs.worldwind.defaults :as default]
            [globe-cljs.worldwind.color :as color]
            [globe-cljs.worldwind.location :as location]
            [globe-cljs.worldwind.position :as position]
            [globe-cljs.worldwind.geographic-text :as geo-text]
            [globe-cljs.worldwind.text-attributes :as text-attr]
            [globe-cljs.worldwind.layer.renderable :as rl]
            [globe-cljs.worldwind.surface.polygon :as poly]))


(def sensor-color-pallet [{:r 0x00 :g 0x80 :b 0x00 :a 0.3}  ; green
                          {:r 0x00 :g 0x00 :b 0xff :a 0.3}  ; blue
                          {:r 0xff :g 0xa5 :b 0x00 :a 0.3}  ; orange
                          {:r 0x80 :g 0x80 :b 0x80 :a 0.3}  ; grey
                          {:r 0x64 :g 0x95 :b 0xed :a 0.3}  ; cornflowerblue
                          {:r 0xff :g 0x8b :b 0x8b :a 0.3}  ; darkcyan
                          {:r 0xda :g 0xa5 :b 0x20 :a 0.3}  ; goldenrod
                          {:r 0xf0 :g 0xe6 :b 0x8c :a 0.3}  ; khaki
                          {:r 0xff :g 0x00 :b 0xff :a 0.3}  ; deepskyblue
                          {:r 0x00 :g 0x00 :b 0x80 :a 0.3}  ; navy
                          {:r 0x00 :g 0xff :b 0xff :a 0.9}  ; cyan
                          {:r 0x8b :g 0x00 :b 0x00 :a 0.3}  ; darkred
                          {:r 0x8f :g 0xbc :b 0x8f :a 0.3}  ; darkseagreen
                          {:r 0x94 :g 0x00 :b 0xd3 :a 0.3}  ; darkviolet
                          {:r 0x22 :g 0x8b :b 0x33 :a 0.3}  ; forestgreen
                          {:r 0xff :g 0xb6 :b 0xc1 :a 0.9}  ; lightpink
                          {:r 0xda :g 0x70 :b 0xd6 :a 0.3}  ; orchid
                          {:r 0xdd :g 0xa0 :b 0xdd :a 0.9}  ; plum
                          {:r 0xff :g 0x63 :b 0x47 :a 0.3}  ; tomato
                          {:r 0xff :g 0x45 :b 0x00 :a 0.3}])


(defn get-sensor-colors [pl]
  (zipmap (into #{} (map key pl)) (cycle sensor-color-pallet)))


(defn- make-polygon [cell sensor]
  (let [cell-text (str cell)
        boundaries (cell/cell-boundaries cell)
        locations  (->> boundaries
                     (map (fn [location]
                            (location/location location)))
                     (into-array))
        center (position/position (get cell/cell-centers cell))]
    {cell-text
     (rl/renderable-layer cell-text
       [(poly/polygon locations {:color (color/yellow 0.3)})
        (geo-text/geographic-text center cell-text
          (text-attr/text-attributes))])}))


(re-frame/reg-sub
  ::subs/layers

  (fn [[_ id] _]
    (re-frame/subscribe [::subs/current-cells id]))

  (fn [cells _]
    (->> cells
      (map #(make-polygon (first (keys %)) (first (vals %))))
      (into {})
      (merge (get-in @rdb/app-db [:widgets id :layers])))))


(comment
  (def children [(poly/polygon [0 0] {:color [255 0 0 1]})
                 (poly/polygon [0 1] {:color [0 255 0 1]})
                 (poly/polygon [1 0] {:color [0 0 255 1]})])

  @(re-frame/subscribe [::subs/current-cells])
  @(re-frame/subscribe [::subs/current-cells])
  @(re-frame/subscribe [::subs/layers])


  (def cell @(re-frame/subscribe [::subs/current-cells]))

  (merge {(str cell)
          (rl/createLayer (str cell)
            [(poly/polygon cell {:color [128 128 0 0.3]})])}
    (:layers @re-frame.db/app-db))

  ())
