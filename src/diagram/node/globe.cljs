(ns diagram.node.globe
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]
            [re-frame.core :as re-frame]

            [globe-cljs.cell.layer-support :as ls]
            [globe-cljs.subs :as subs]
            [globe-cljs.events :as events]

            ["react-flow-renderer" :refer (Handle)]
            ["react-ui-cards" :refer (FlippingCard FlippingCardBack FlippingCardFront)]

            [diagram.card.globe :as globe]
            [diagram.node.utils :as u]))


(defn globe-node [data]
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
       [:> FlippingCard {:style (merge u/node-style-globe {:margin 0})}
        [:> FlippingCardFront {:style u/node-style-globe}
         [globe/card {} {:id                 globe-id
                         :starting-date-time ls/starting-date-time
                         :time-t             time-t
                         :projection         projection
                         :base-layers        base-layers
                         :sensor-layers      sensor-layers}]]
        [:> FlippingCardBack {:style u/node-style-globe}
         [:div {:style (merge u/node-style-globe
                         {:background      u/default-background
                          :display         :flex
                          :flex-direction  :column
                          :justify-content :center
                          :align-items     :center})}
          "The Back"]]]

       [:> Handle {:id    (str globe-id "-out") :type "source" :position "right"
                   :style {:borderRadius "true 0 true true"}}]])))
