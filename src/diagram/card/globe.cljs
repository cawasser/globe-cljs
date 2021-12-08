(ns diagram.card.globe
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            [globe.globe :as globe]
            [cljs-time.core :as cljs-time]
            [cljs-time.coerce :as coerce]

            [diagram.node.utils :as u]))


(defn card [props {:keys [id starting-date-time time-t
                          projection base-layers sensor-layers]}]
  (reagent/as-element
    [:div {:style (merge u/node-style-globe props)}
     [globe/globe {:id         id
                   :min-max    :min
                   :time       (coerce/to-date
                                 (cljs-time/plus starting-date-time
                                   (cljs-time/hours (or @time-t 0))))
                   :projection (or @projection "3D")
                   :style      {:width            "100%"
                                :height           "100%"}}
      (merge @base-layers @sensor-layers)]]))