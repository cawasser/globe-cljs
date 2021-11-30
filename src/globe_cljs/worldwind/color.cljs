(ns globe-cljs.worldwind.color
  (:require ["worldwindjs" :as WorldWind]
            [taoensso.timbre :as log]))


(defn color
  ([r g b a]
   (WorldWind/Color. r g b a))

  ([[r g b a]]
   (WorldWind/Color. r g b a)))
