(ns globe.worldwind.surface.circle
  (:require ["worldwindjs" :as WorldWind]
            [taoensso.timbre :as log]

            [globe.worldwind.shape-attributes :as shape-attributes]))


(defn circle [location props]
  (let [[r g b a] (:color props)
        attributes (shape-attributes/shape-attributes
                     {:interior-color [r g b 1.0]
                      :outline-color [r g b 1.0]
                      :outline-width 2})]
    (log/info "circle" location props)

    (WorldWind/SurfaceCircle. location 300000 attributes)))