(ns globe-cljs.layer.blue-marble
  (:require ["worldwindjs" :as WorldWind]
            [taoensso.timbre :as log]))


(defn- createLayer [layer-name]
  (log/info "createLayer" layer-name)

  (let [layer (WorldWind/BMNGLayer.)]
    (set! (.-displayName layer) layer-name)
    layer))

