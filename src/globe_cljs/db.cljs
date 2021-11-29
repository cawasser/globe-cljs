(ns globe-cljs.db
  (:require [globe-cljs.layer.blue-marble :as bm]
            [globe-cljs.layer.compass :as compass]))

(def default-db
  {:name         "re-frame"
   :current-cell [0 0]
   :base-layers  {"Blue Marble" (bm/createLayer "Blue Marble")
                  "Compass"     (compass/createLayer "Compass")}
   :layers       {}})
