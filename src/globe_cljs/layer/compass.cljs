(ns globe-cljs.layer.compass
  (:require ["worldwindjs" :as WorldWind]
            [taoensso.timbre :as log]))


(defn createLayer [layer-name]
  (log/info "createLayer" layer-name)

  (let [layer (WorldWind/CompassLayer.)]
    (set! (.-displayName layer) layer-name)
    layer))
