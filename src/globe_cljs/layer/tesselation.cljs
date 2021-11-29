(ns globe-cljs.layer.tesselation
  (:require ["worldwindjs" :as WorldWind]
            [taoensso.timbre :as log]))


(defn createLayer [layer-name]
  (log/info "createLayer" layer-name)

  (let [layer (WorldWind/ShowTessellationLayer.)]
    (set! (.-displayName layer) layer-name)
    layer))





