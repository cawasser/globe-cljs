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


(def sensor-color-pallet [[:green [0.0 0.5 0.0 0.3]]        ; "abi-3"
                          [:blue [0.0 0. 1.0 0.3]]          ; "abi-1"
                          [:orange [1.0 0.65 0.0 0.3]]      ; "avhhr-6"
                          [:grey [0.5 0.5 0.5 0.3]]         ; "viirs-5"
                          [:cornflowerblue [0.4 0.58 0.93 0.3]] ; "abi-meso-11"
                          [:darkcyan [1.0 0.55 0.55 0.3]]   ; "abi-meso-4"
                          [:goldenrod [0.84 0.65 0.13 0.3]] ; "abi-meso-10"
                          [:khaki [0.94 0.90 0.55 0.3]]     ; "abi-meso-2"
                          [:deepskyblue [1.0 0.0 1.0 0.3]]
                          [:navy [0.0 0.0 0.5 0.3]]
                          [:cyan [0.0 1.0 1.0 0.9]]
                          [:darkred [0.55 0.0 0.0 0.3]]
                          [:darkseagreen [0.55 0.74 0.56 0.3]]
                          [:darkviolet [0.58 0 0.83 0.3]]
                          [:forestgreen [1.0 0.71 0.76 0.9]]
                          [:orchid [0.84 0.44 0.84 0.3]]
                          [:plum [0.87 0.63 0.87 0.9]]
                          [:tomato [1.0 0.39 0.28 0.3]]
                          [:orangered [1.0 0.27 0.0 0.3]]])


(defn- get-sensor-colors [sensors]
  (if (seq sensors)
    (zipmap sensors (cycle sensor-color-pallet))
    []))


(defn- time-slider [id current-time-t]
  [:div.slidecontainer
   [:input#myRange.slider
    {:style     {:width 400}
     :type      "range" :min "0" :max "100" :value @current-time-t
     :on-change #(do
                   ;(log/info "time-slider" (js/parseInt (-> % .-target .-value)))
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


(defn- sensor-visibility-control [globe-id sensors selected-sensors colors]
  [:div {:flexdirection :row
         :flexwrap      :wrap}
   (doall
     (for [s @sensors]
       ^{:key s}
       [:div {:style {:background-color (first (get colors s))}}
        [:input {:type     "checkbox"
                 :value    (contains? @selected-sensors s)
                 :on-click #(re-frame/dispatch-sync
                              [::events/toggle-sensor globe-id s])}]
        [:label s]]))])


(defn- globe [globe-id]
  (let [sensors          (re-frame/subscribe [::subs/sensor-types])
        selected-sensors (re-frame/subscribe [::subs/selected-sensors])
        colors           (get-sensor-colors @sensors)
        base-layer       (re-frame/subscribe [::subs/base-layers globe-id])
        layers           (re-frame/subscribe [::subs/layers globe-id colors])
        projection       (re-frame/subscribe [::subs/projection globe-id])
        time-t           (re-frame/subscribe [::subs/time globe-id])]

    [:div
     [:div
      [:h1 globe-id]

      [time-slider globe-id time-t]

      [:div
       [sensor-visibility-control globe-id sensors selected-sensors colors]
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


; set up the colors for the sensors
(comment
  (def sensors (re-frame/subscribe [::subs/sensor-types]))
  (def colors (get-sensor-colors @sensors))

  (def sensor-allocs @(re-frame/subscribe [::subs/sensor-allocations]))


  ())


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
