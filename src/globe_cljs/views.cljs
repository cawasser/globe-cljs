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


(defn- time-slider [id current-time-t]
  [:div.slidecontainer
   [:input#myRange.slider
    {:style     {:width 400}
     :type      "range" :min "0" :max "100" :value @current-time-t
     :on-change #(do
                   (log/info "time-slider" (js/parseInt (-> % .-target .-value)))
                   (re-frame/dispatch-sync [::events/update-time id (js/parseInt (-> % .-target .-value))]))}]])


(defn- projection-control [globe-id projections]
  [:div
   [:label {:for "projections"} "Projection:"]
   [:select#projections {:name      "projections"
                         :value     @projections
                         :on-change #(re-frame/dispatch
                                       [::events/set-projection globe-id (-> % .-target .-value)])}
    (doall
      (map (fn [p]
             ^{:key p} [:option {:value p} p])
        g/projections))]])


(defn- sensor-visibility-control [globe-id sensors selected-sensors]
  [:div {:flexdirection :row
         :flexwrap :wrap}
   (for [s @sensors]
     [:div
      [:input {:type     "checkbox"
               :value    (contains? @selected-sensors s)
               :on-click #(re-frame/dispatch-sync
                            [::events/toggle-sensor globe-id s])}]
      [:label s]])])


(defn- globe [globe-id]
  (let [base-layer       (re-frame/subscribe [::subs/base-layers globe-id])
        layers           (re-frame/subscribe [::subs/layers globe-id])
        projection       (re-frame/subscribe [::subs/projection globe-id])
        time-t           (re-frame/subscribe [::subs/time globe-id])
        sensors          (re-frame/subscribe [::subs/sensor-types])
        selected-sensors (re-frame/subscribe [::subs/selected-sensors])]

    [:div
     [:div
      [:h1 globe-id]

      [time-slider globe-id time-t]

      [:div
       [sensor-visibility-control globe-id sensors selected-sensors]
       [projection-control globe-id projection]]]

     [g/globe {:id         globe-id
               :projection (or @projection "3D")
               :style      {:background-color :lightblue
                            :width            "50%" :height "100%"}}
      (merge @base-layer @layers)]]))


(defn main-panel []
  [:div
   [globe "globe-1"]
   [globe "globe-2"]])





(comment
  (def globe-id "globe-1")
  (def base-layer (re-frame/subscribe [::subs/base-layers globe-id]))
  (def layers (re-frame/subscribe [::subs/layers globe-id]))

  (g/globe {:id "my-globe"} @layers)

  (merge @base-layer @layers)

  @re-frame.db/app-db

  (map (fn [[k v :as l]] {:k k :l l})
    (:layers @re-frame.db/app-db))


  ())
