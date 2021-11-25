(ns globe-cljs.views
  (:require
    [reagent.core :as r]
    [re-frame.core :as re-frame]
    [globe-cljs.subs :as subs]

    [globe-cljs.globe :as g]
    [globe-cljs.reagent-context :as rc]
    [globe-cljs.renderableLayer :as l]))


(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        layers (re-frame/subscribe [::subs/layers])]
    [:div
     [:div
      [:h1
       "Watch for the compass!"]
      [g/globe {:id "my-first-globe"
                :style {:background-color :lightblue
                        :width "50%" :height "100%"}}
       @layers]]]))
     ;[:div
     ; [:h1 "Provider/Consumer using Context"]
     ; [rc/root]]]))


(comment
  (def layers (re-frame/subscribe [::subs/layers]))

  (g/globe {:id "my-globe"} @layers)


  @re-frame.db/app-db

  (map (fn [[k v :as l]] {:k k :l l})
    (:layers @re-frame.db/app-db))


  ())
