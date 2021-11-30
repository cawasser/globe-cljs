(ns globe-cljs.worldwind.text-attributes
  (:require ["worldwindjs" :as WorldWind]
            [taoensso.timbre :as log]

            [globe-cljs.worldwind.defaults :as defaults]))


(defn text-attributes
  ([]
   (text-attributes defaults/text-color))

  ([text-color]
   (let [text-attributes  (WorldWind/TextAttributes.)]
     (set! (.-color text-attributes) text-color)
     text-attributes)))