(ns globe-cljs.db
  (:require [globe.worldwind.layer.blue-marble :as blue-marble]
            [globe.worldwind.layer.compass :as compass]
            [globe.worldwind.layer.star-field :as star-field]
            [globe.worldwind.layer.night :as night]
            [globe-cljs.sensor-data :as sd]

            [taoensso.timbre :as log]))


(defn globe-config [globe-id min-max]
  (log/info "globe-config" globe-id min-max)
  {:projection       "3D"
   :base-layers      (merge {(str globe-id " Blue Marble") (blue-marble/blue-marble (str globe-id " Blue Marble"))
                             (str globe-id " Night")       (night/night (str globe-id " Night"))}
                       (if (= :min min-max)
                         {}
                         {(str globe-id " Compass")  (star-field/star-field (str globe-id " Compass"))
                          (str globe-id " Star Field")  (star-field/star-field (str globe-id " Star Field"))}))
   :selected-sensors #{}
   :selected-aois    #{}
   :layers           {}
   :time             0})


(def default-db
  {:name               "re-frame"
   :sensor-allocations sd/all-sensor-data
   :aois               sd/all-aoi-data
   :diagram-elements   sd/flow-elements})



(comment
  (globe-config "dummy" :min)
  (globe-config "dummy" :max)

  ())


