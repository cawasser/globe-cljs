(ns globe-cljs.surface.polygon
  (:require ["worldwindjs" :as WorldWind]
            [taoensso.timbre :as log]

            [re-frame.core :as re-frame]
            [globe-cljs.subs :as subs]

            [globe-cljs.cell.util :as cell]
            [globe-cljs.worldwind.location :as location]
            [globe-cljs.worldwind.shape-attributes :as shape-attributes]
            [globe-cljs.layer.renderable :as rl]))


(defn polygon [locations props]
  (log/info "polygon" locations props)

  (let [[r g b a] (:color props)
        attributes (shape-attributes/shape-attributes
                     {:interior-color [r g b a]
                      :outline-color [r g b 1.0]
                      :outline-width 2})]
    (WorldWind/SurfacePolygon. locations attributes)))


(re-frame/reg-sub
  ::subs/layers

  (fn [[_ id] _]
    (re-frame/subscribe [::subs/current-cell id]))

  (fn [cell [_ id]]
    (if cell
      (let [boundaries (cell/cell-boundaries cell)
            locations  (->> boundaries
                         (map (fn [location]
                                (location/location location)))
                         (into-array))]
        (merge {(str cell)
                (rl/renderable-layer (str cell)
                  [(polygon locations {:color [128 128 0 0.3]})])}
          (:layers @re-frame.db/app-db))))))



(comment
  (def children [(polygon [0 0] {:color [255 0 0 1]})
                 (polygon [0 1] {:color [0 255 0 1]})
                 (polygon [1 0] {:color [0 0 255 1]})])

  @(re-frame/subscribe [::subs/current-cell])
  @(re-frame/subscribe [::subs/current-cell])
  @(re-frame/subscribe [::subs/layers])


  (def cell @(re-frame/subscribe [::subs/current-cell]))

  (merge {(str cell)
          (rl/createLayer (str cell)
            [(polygon cell {:color [128 128 0 0.3]})])}
    (:layers @re-frame.db/app-db))

  ())