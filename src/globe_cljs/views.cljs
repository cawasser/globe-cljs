(ns globe-cljs.views
  (:require
    [taoensso.timbre :as log]

    [globe-cljs.diagram-component :as d]
    [globe-cljs.globe-component :as g]))

    ;[chart.pie-chart :as c]
    ;[diagram.node.utils :as u]
    ;[diagram.card.video :as video]))


(defn main-panel []
  [:div
   [:link {:rel "stylesheet" :href "/css/bulma.css"}]

   [g/globe "globe-1"]
   [d/diagram]])


