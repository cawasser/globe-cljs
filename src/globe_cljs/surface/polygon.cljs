(ns globe-cljs.surface.polygon
  (:require ["worldwindjs" :as WorldWind]
            [taoensso.timbre :as log]

            [globe-cljs.worldwind.shape-attributes :as shape-attributes]))


(defn polygon [locations props]
  (log/info "polygon" locations props)

  (let [[r g b a] (:color props)
        attributes (shape-attributes/shape-attributes
                     {:interior-color [r g b a]
                      :outline-color [r g b 1.0]
                      :outline-width 2})]
    (WorldWind/SurfacePolygon. locations attributes)))
