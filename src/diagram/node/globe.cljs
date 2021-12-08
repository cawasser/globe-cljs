(ns diagram.node.globe
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]
            [re-frame.core :as re-frame]

            [globe.globe :as globe]
            [globe-cljs.cell.layer-support :as ls]
            [cljs-time.core :as cljs-time]
            [cljs-time.coerce :as coerce]

            [globe-cljs.subs :as subs]
            [globe-cljs.events :as events]

            ["react-flow-renderer" :refer (Handle)]))


(defn globe-node [data]
  (let [d             (js->clj data)
        sensor        (get-in d ["data" "sensor"])
        globe-id      (str "globe-" sensor)
        sensors       (re-frame/subscribe [::subs/sensor-types])
        colors        (ls/get-sensor-colors @sensors)
        base-layer    (re-frame/subscribe [::subs/base-layers globe-id])
        projection    (re-frame/subscribe [::subs/projection globe-id])
        sensor-layers (re-frame/subscribe [::subs/sensor-layers globe-id colors])
        time-t        (re-frame/subscribe [::subs/time globe-id])]

    (re-frame/dispatch-sync [::events/init-widget globe-id :min])
    (re-frame/dispatch-sync [::events/toggle-sensor globe-id sensor])

    (reagent/as-element
      [:div {:style {:width         "150px" :height "150px"
                     :border-radius "50%"
                     :border        "solid 1px"}}

       [:> Handle {:id    (str globe-id "-out") :type "source" :position "right"
                   :style {:borderRadius "true 0 true true"}}]

       [globe/globe {:id         globe-id
                     :min-max    :min
                     :time       (coerce/to-date
                                   (cljs-time/plus ls/starting-date-time
                                     (cljs-time/hours (or @time-t 0))))
                     :projection (or @projection "3D")
                     :style      {:width            "100%"
                                  :height           "100%"}}
        (merge @base-layer @sensor-layers)]])))             ;[:p sensor]])))
