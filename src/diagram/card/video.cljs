(ns diagram.card.video
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]
            [diagram.node.utils :as u]

            ["react-player" :default ReactPlayer]))


(defn card [props video]
  (reagent/as-element
    [:div#video (merge {:style u/node-style-square} props)
     [:> ReactPlayer {:url     video
                      :playing true
                      :loop    true
                      :muted   true
                      :volume  0
                      :pip     false
                      :width   "100%"
                      :height  "100%"}]]))