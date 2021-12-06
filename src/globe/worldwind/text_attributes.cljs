(ns globe.worldwind.text-attributes
  (:require ["worldwindjs" :as WorldWind]
            [taoensso.timbre :as log]

            [globe.worldwind.defaults :as defaults]
            [globe.worldwind.offset :as offset]))


(defn text-attributes
  ([]
   (text-attributes defaults/text-color))

  ([text-color]
   (let [text-attributes  (WorldWind/TextAttributes.)]
     (set! (.-color text-attributes) text-color)
     (set! (.-depthTest text-attributes) false)
     (set! (.-scale text-attributes) defaults/scale)
     ;(set! (.-offset text-attributes) (offset/offset))
     text-attributes)))
