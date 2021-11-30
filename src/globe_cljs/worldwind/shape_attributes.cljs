(ns globe-cljs.worldwind.shape-attributes
  (:require ["worldwindjs" :as WorldWind]
            [taoensso.timbre :as log]

            [globe-cljs.worldwind.defaults :as default]
            [globe-cljs.worldwind.color :as color]))


(defn shape-attributes [{:keys [interior-color outline-width outline-color]}]
  (let [attributes (WorldWind/ShapeAttributes.)]
    (set! (.-interiorColor attributes) (color/color (or interior-color default/interior-color)))
    (set! (.-outlineWidth attributes) (or outline-width default/outline-width))
    (set! (.-outlineColor attributes) (color/color (or outline-color default/outline-color)))

    attributes))