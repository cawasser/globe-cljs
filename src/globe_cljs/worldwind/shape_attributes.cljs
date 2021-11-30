(ns globe-cljs.worldwind.shape-attributes
  (:require ["worldwindjs" :as WorldWind]
            [taoensso.timbre :as log]

            [globe-cljs.worldwind.color :as color]))


(def default-color [128 128 128 1.0])


(defn shape-attributes [{:keys [interior-color outline-width outline-color]}]
  (let [attributes (WorldWind/ShapeAttributes.)]
    (set! (.-interiorColor attributes) (color/color (or interior-color default-color)))
    (set! (.-outlineWidth attributes) (or outline-width 2))
    (set! (.-outlineColor attributes) (color/color (or outline-color default-color)))

    attributes))