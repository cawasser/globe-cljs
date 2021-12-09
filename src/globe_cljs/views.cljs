(ns globe-cljs.views
  (:require
    [taoensso.timbre :as log]
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]

    [globe-cljs.globe-component :as g]
    [globe-cljs.subs :as subs]

    [globe-cljs.flow-diagram :as flow]
    [diagram.node.globe :as globe]
    [diagram.node.platform :as platform]
    [diagram.node.downlink-terminal :as downlink-terminal]
    [diagram.node.processing-center :as processing-center]
    [diagram.node.server :as server]
    [diagram.node.service :as service]
    [diagram.node.event-channel :as event-channel]
    [diagram.node.view-channel :as view-channel]))


(defn- weather-flow [props]
  (reagent/with-let [weather-flow-elements (re-frame/subscribe [::subs/weather-flow-elements])]
    [flow/diagram props
     weather-flow-elements
     {"globe"             globe/node
      "platform"          platform/node
      "downlink-terminal" downlink-terminal/node
      "processing-center" processing-center/node}
     {}]))


(defn- system-flow [props]
  (reagent/with-let [system-flow-elements (re-frame/subscribe [::subs/system-flow-elements])]
    [flow/diagram props
     system-flow-elements
     {"server"        server/node
      "service"       service/node
      "event-channel" event-channel/node
      "view-channel"  view-channel/node}
     {}]))


(defn main-panel []
  [:div
   [:link {:rel "stylesheet" :href "/css/bulma.css"}]

   [g/globe "globe-1"]

   ;[weather-flow {:is "weather"
   ;               :style {:width "70%" :height "700px"}}]

   [system-flow {:id "system"
                 :style {:width "70%" :height "900px"
                         :border-width "2px solid"}}]])




