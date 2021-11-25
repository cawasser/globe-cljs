(ns globe-cljs.db
  (:require [globe-cljs.layer.blue-marble :as bm]))

(def default-db
  {:name   "re-frame"
   :layers {"Blue Marble" (bm/createLayer "Blue Marble")}})
