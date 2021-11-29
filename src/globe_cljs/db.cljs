(ns globe-cljs.db
  (:require [globe-cljs.layer.blue-marble :as bm]))

(def default-db
  {:name   "re-frame"
   :current-cell [0 0]
   :base-layer {"Blue Marble" (bm/createLayer "Blue Marble")}
   :layers {}})
