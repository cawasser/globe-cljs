(ns globe-cljs.db
  (:require [globe-cljs.layer.blue-marble :as bm]
            [globe-cljs.layer.compass :as compass]))


(def globe-config
  {:current-cell [0 0]
   :projection "3D"
   :base-layers  {"Blue Marble" (bm/createLayer "Blue Marble")
                  "Compass"     (compass/createLayer "Compass")}
   :layers       {}})


(def default-db
  {:name         "re-frame"})

