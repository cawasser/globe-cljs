(ns globe-cljs.db
  (:require [globe-cljs.layer.blue-marble :as blue-marble]
            [globe-cljs.layer.compass :as compass]))


(def globe-config
  {:current-cell [0 0]
   :projection "3D"
   :base-layers  {"Blue Marble" (blue-marble/blue-marble "Blue Marble")
                  "Compass"     (compass/compass "Compass")}
   :layers       {}})


(def default-db
  {:name         "re-frame"})

