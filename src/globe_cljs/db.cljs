(ns globe-cljs.db
  (:require [globe-cljs.worldwind.layer.blue-marble :as blue-marble]
            [globe-cljs.worldwind.layer.compass :as compass]
            [globe-cljs.sensor-data :as sd]))


(def globe-config
  {:projection   "3D"
   :base-layers  {"Blue Marble" (blue-marble/blue-marble "Blue Marble")
                  "Compass"     (compass/compass "Compass")}
   :layers       {}
   :timer        0})


(def default-db
  {:name "re-frame"
   :sensor-allocations sd/sensor-data})

