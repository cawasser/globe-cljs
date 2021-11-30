(ns globe-cljs.cell.layer-support
  (:require [taoensso.timbre :as log]

            [re-frame.core :as re-frame]
            [globe-cljs.subs :as subs]

            [globe-cljs.cell.util :as cell]
            [globe-cljs.worldwind.defaults :as default]
            [globe-cljs.worldwind.location :as location]
            [globe-cljs.worldwind.position :as position]
            [globe-cljs.worldwind.geographic-text :as geo-text]
            [globe-cljs.worldwind.text-attributes :as text-attr]
            [globe-cljs.layer.renderable :as rl]
            [globe-cljs.surface.polygon :as poly]))


(defn- make-polygon [cell]
  (let [cell-text (str cell)
        boundaries (cell/cell-boundaries cell)
        locations  (->> boundaries
                     (map (fn [location]
                            (location/location location)))
                     (into-array))
        center (position/position (get cell/cell-centers cell))]
    {cell-text
     (rl/renderable-layer cell-text
       [(poly/polygon locations {:color [128 128 0 0.3]})
        (geo-text/geographic-text center cell-text
          (text-attr/text-attributes))])}))


(re-frame/reg-sub
  ::subs/layers

  (fn [[_ id] _]
    (re-frame/subscribe [::subs/current-cells id]))

  (fn [cells _]
    (->> cells
      (map #(make-polygon %))
      (into {})
      (merge (:layers @re-frame.db/app-db)))))


(comment
  (def cells [[0 0]])
  (->> cells
    (map #(make-polygon %))
    (into {})
    (merge (:layers @re-frame.db/app-db)))

  ())

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

