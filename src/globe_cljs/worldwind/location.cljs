(ns globe-cljs.worldwind.location
  (:require ["worldwindjs" :as WorldWind]
            [taoensso.timbre :as log]))


(defn createLocation [[lat lon]]
  (WorldWind/Location. lat lon))
