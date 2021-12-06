(ns globe-cljs.db
  (:require [globe.worldwind.layer.blue-marble :as blue-marble]
            [globe.worldwind.layer.compass :as compass]
            [globe.worldwind.layer.star-field :as star-field]
            [globe.worldwind.layer.night :as night]
            [globe-cljs.sensor-data :as sd]))


(defn globe-config [globe-id]
  {:projection       "3D"
   :base-layers      {(str globe-id " Blue Marble") (blue-marble/blue-marble (str globe-id " Blue Marble"))
                      (str globe-id " Compass")     (compass/compass (str globe-id " Compass"))
                      (str globe-id " Star Field")  (star-field/star-field (str globe-id " Star Field"))
                      (str globe-id " Night")       (night/night (str globe-id " Night"))}
   :selected-sensors #{}
   :selected-aois    #{}
   :layers           {}
   :time             0})


(def default-db
  {:name               "re-frame"
   :sensor-allocations sd/all-sensor-data
   :aois               sd/all-aoi-data
   :diagram-elements   sd/flow-elements})

