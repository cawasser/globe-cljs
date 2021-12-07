(ns diagram.node.platform
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



(defn platform [data]
  (let [d (js->clj data)
        label (get-in d ["data" "label"])
        id (get d "id")]

    (reagent/as-element
      [:div {:style {:width "150px" :height "150px"
                     :background "#9CA8B3"
                     :border-radius "50%"
                     :color "#FFF"
                     :padding 10}}
       [:img {:style {:width "100px" :height "100px"}
              :src "/images/icons/Weather-Satellite-PNG-Clipart.png"}]
       [:div label]
       [:> Handle {:id (str id "-out") :type "source" :position "right"
                   :style {:borderRadius "true 0 true true"}}]
       [:> Handle {:id (str id "-in") :type "target" :position "left"
                   :style {:borderRadius "true 0 true true"}}]])))
