(ns globe-cljs.views
  (:require
    [reagent.core :as r]
    [re-frame.core :as re-frame]
    [globe-cljs.subs :as subs]
    [globe-cljs.events :as events]
    [taoensso.timbre :as log]

    [globe-cljs.globe :as g]
    [globe-cljs.reagent-context :as rc]
    [globe-cljs.renderableLayer :as l]))


(defn main-panel []
  (let [base-layer (re-frame/subscribe [::subs/base-layers])
        layers     (re-frame/subscribe [::subs/layers])
        projection (re-frame/subscribe [::subs/projection])
        timer      (re-frame/subscribe [::subs/timer])]
    [:div
     [:div
      [:h1
       "Watch the moving grid-cell!"]

      [:button.button {:on-click #(re-frame/dispatch-sync [::events/toggle-timer])}
       (if @timer "stop" "start")]

      [:div
       [:label {:for "projections"} "Projection:"]
       [:select#projections {:name "projections"
                             :value @projection
                             :on-change #(re-frame/dispatch [::events/set-projection (-> % .-target .-value)])}
        (doall
          (map (fn [p]
                 ^{:key p} [:option {:value p} p])
            g/projections))]]]

     [g/globe {:id         "my-first-globe"
               :projection @projection
               :style      {:background-color :lightblue
                            :width            "50%" :height "100%"}}
      (merge @base-layer @layers)]]))


(comment
  (def base-layer (re-frame/subscribe [::subs/base-layer]))
  (def layers (re-frame/subscribe [::subs/layers]))

  (g/globe {:id "my-globe"} @layers)

  (merge @base-layer @layers)

  @re-frame.db/app-db

  (map (fn [[k v :as l]] {:k k :l l})
    (:layers @re-frame.db/app-db))


  ())
