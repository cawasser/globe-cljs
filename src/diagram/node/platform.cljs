(ns diagram.node.platform
  (:require [taoensso.timbre :as log]
            [reagent.core :as reagent]

            ["react-flow-renderer" :refer (Handle)]

            [diagram.node.utils :as u]))


(defn platform [data]
  (let [d (js->clj data)
        label (get-in d ["data" "label"])
        id (get d "id")]

    (reagent/as-element
      [:div {:style u/node-style-round}
       [:img {:style u/image-style
              :src "/images/icons/Weather-Satellite-PNG-Clipart.png"}]
       [:div u/label-style label]
       [:> Handle {:id (str id "-out") :type "source" :position "right"
                   :style {:borderRadius "true 0 true true"}}]
       [:> Handle {:id (str id "-in") :type "target" :position "left"
                   :style {:borderRadius "true 0 true true"}}]])))
