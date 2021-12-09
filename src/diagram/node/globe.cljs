(ns diagram.node.globe
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]
            [re-frame.core :as re-frame]

            [globe-cljs.cell.layer-support :as ls]
            [globe-cljs.subs :as subs]
            [globe-cljs.events :as events]

            ["react-flow-renderer" :refer (Handle)]

            [diagram.card.flippable :as f]
            [diagram.card.globe :as globe]
            [diagram.card.video :as video]
            [diagram.node.utils :as u]))


(defn node [data]
  (let [d             (js->clj data)
        sensor        (get-in d ["data" "sensor"])
        globe-id      (str "globe-" sensor)
        sensors       (re-frame/subscribe [::subs/sensor-types])
        colors        (ls/get-sensor-colors @sensors)
        base-layers   (re-frame/subscribe [::subs/base-layers globe-id])
        projection    (re-frame/subscribe [::subs/projection globe-id])
        sensor-layers (re-frame/subscribe [::subs/sensor-layers globe-id colors])
        time-t        (re-frame/subscribe [::subs/time globe-id])]

    (re-frame/dispatch-sync [::events/init-widget globe-id :min])
    (re-frame/dispatch-sync [::events/toggle-sensor globe-id sensor])

    (reagent/as-element
      [:div#globe-card {:style u/node-style-globe}
       [f/flippable {:style (merge u/node-style-globe {:margin 0})}
        [globe/card {:style u/node-style-globe}
         {:id                 globe-id
          :starting-date-time ls/starting-date-time
          :time-t             time-t
          :projection         projection
          :base-layers        base-layers
          :sensor-layers      sensor-layers}]
        [video/card {:style u/node-style-globe}
         "https://www.youtube.com/watch?v=z33-qOXOWS4"]]
          ;"https://www.youtube.com/watch?v=hcYeZMNAbNs"]]]
          ;"https://www.youtube.com/watch?v=vLgsf8Pei6Q"]]]

       [:> Handle {:id    (str globe-id "-out") :type "source" :position "right"
                   :style {:borderRadius "true 0 true true"}}]])))
