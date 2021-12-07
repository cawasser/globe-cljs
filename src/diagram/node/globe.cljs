(ns diagram.node.globe
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]
            [re-frame.core :as re-frame]

            [globe.globe :as globe]
            [globe-cljs.cell.layer-support :as ls]
            [cljs-time.core :as cljs-time]
            [cljs-time.coerce :as coerce]

            [globe-cljs.subs :as subs]
            [globe-cljs.events :as events]))



(comment


  ())

(defn globe-node [data]
  (let [d          (js->clj data)
        sensor     (get-in d ["data" "sensor"])
        globe-id   (str "globe-" sensor)
        sensors    (re-frame/subscribe [::subs/sensor-types])
        colors     (ls/get-sensor-colors @sensors)
        base-layer (re-frame/subscribe [::subs/base-layers globe-id])
        projection (re-frame/subscribe [::subs/projection globe-id])
        layers     (re-frame/subscribe [::subs/layers globe-id colors])
        time-t     (re-frame/subscribe [::subs/time globe-id])]

    (re-frame/dispatch-sync [::events/init-widget globe-id :min])
    (re-frame/dispatch-sync [::events/toggle-sensor globe-id sensor])

    (reagent/as-element
      [:div {:style {:width  "200px" :height "200px"
                     :border "solid 1px"}}
       [globe/globe {:id         globe-id
                     :time       (coerce/to-date
                                   (cljs-time/plus ls/starting-date-time
                                     (cljs-time/hours (or @time-t 0))))
                     :projection (or @projection "3D")
                     :style      {:background-color :black
                                  :width            "100%"
                                  :height           "100%"}}
        (merge @base-layer @layers)]])))                 ;[:p sensor]])))
