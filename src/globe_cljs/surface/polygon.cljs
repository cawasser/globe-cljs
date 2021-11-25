(ns globe-cljs.surface.polygon
  (:require ["worldwindjs" :as WorldWind]
            [taoensso.timbre :as log]

            [globe-cljs.cell.util :as cell]))




(defn createPolygon [cell props]
  (log/info "createPolygon" cell props)

  (let [attributes (WorldWind/ShapeAttributes.)
        locations (cell/boundary-locations cell)
        polygon (WorldWind/SurfacePolygon. locations attributes)
        [r g b a] (:color props)]

    (set! (.-interiorColor attributes) (WorldWind/Color. r g b a))
    (set! (.-outlineWidth attributes) 2)
    (set! (.-outlineColor attributes) (WorldWind/Color. r g b 1.0))
    polygon))




(comment
  (def children [(createPolygon [0 0] {:color [255 0 0 1]})
                 (createPolygon [0 1] {:color [0 255 0 1]})
                 (createPolygon [1 0] {:color [0 0 255 1]})])

  ())