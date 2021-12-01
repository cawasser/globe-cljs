(ns globe-cljs.views
  (:require
    [reagent.core :as r]
    [re-frame.core :as re-frame]
    [globe-cljs.subs :as subs]
    [globe-cljs.events :as events]
    [taoensso.timbre :as log]

    [globe-cljs.globe.globe :as g]))


(def sensor-color-pallet [[:green "rgba(0, 128, 0, .3)" [0.0 0.5 0.0 0.3]] ; "abi-3"
                          [:blue "rgba(0, 0, 255, .3)" [0.0 0. 1.0 0.3]] ; "abi-1"
                          [:orange "rgba(255, 165, 0, .3)" [1.0 0.65 0.0 0.3]] ; "avhhr-6"
                          [:grey "rgba(128, 128, 128, .3)" [0.5 0.5 0.5 0.3]] ; "viirs-5"
                          [:cornflowerblue "rgba(100, 149, 237, .3)" [0.4 0.58 0.93 0.3]] ; "abi-meso-11"
                          [:darkcyan "rgba(0, 139, 139, .3)" [1.0 0.55 0.55 0.3]] ; "abi-meso-4"
                          [:goldenrod "rgba(218, 165, 32, .3)" [0.84 0.65 0.13 0.3]] ; "abi-meso-10"
                          [:khaki "rgba(240, 230, 140, .3)" [0.94 0.90 0.55 0.3]] ; "abi-meso-2"
                          [:deepskyblue "rgba(0, 191, 255, .3)" [1.0 0.0 1.0 0.3]]
                          [:navy "rgba(0, 0, 128, .3)" [0.0 0.0 0.5 0.3]]
                          [:cyan "rgba(0, 255, 255, .3)" [0.0 1.0 1.0 0.9]]
                          [:darkred "rgba(139, 0, 0, .3)" [0.55 0.0 0.0 0.3]]
                          [:darkseagreen "rgba(143, 188, 143, .3)" [0.55 0.74 0.56 0.3]]
                          [:darkviolet "rgba(148, 0, 211, .3)" [0.58 0 0.83 0.3]]
                          [:forestgreen "rgba(34, 139, 34, .3)" [1.0 0.71 0.76 0.9]]
                          [:orchid "rgba(218, 112, 214, .3)" [0.84 0.44 0.84 0.3]]
                          [:plum "rgba(221, 160, 221, .3)" [0.87 0.63 0.87 0.9]]
                          [:tomato "rgba(255, 99, 71, .3)" [1.0 0.39 0.28 0.3]]
                          [:orangered "rgba(255, 69, 0, .3)" [1.0 0.27 0.0 0.3]]])


(defn- get-sensor-colors [sensors]
  (if (seq sensors)
    (zipmap sensors (cycle sensor-color-pallet))
    []))


(defn- time-slider [id current-time-t]
  [:div.slidecontainer
   [:input#myRange.slider
    {:style     {:width 400}
     :type      "range" :min "0" :max "9" :value @current-time-t
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
  [:div {:style {:display        :flex
                 :flex-direction :row}}
   (doall
     (for [s @sensors]
       ^{:key s}
       [:div {:style {:background-color (second (get colors s))
                      :border           "solid 2px"
                      :border-color     (first (get colors s))
                      :margin           "5px"
                      :padding          "5px"}}
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

    (re-frame/dispatch-sync [::events/init-widget globe-id])

    (fn []
      [:div
       [:div
        [:h1 globe-id]

        [time-slider globe-id time-t]

        [:div
         [sensor-visibility-control globe-id sensors selected-sensors colors]
         [projection-control globe-id projection]]]

       [g/globe {:id         globe-id
                 :projection (or @projection "3D")
                 :style      {:background-color :black
                              :width            "50%" :height "100%"}}
        (merge @base-layer @layers)]])))
;@base-layer]])))


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


; try updating the app-db and seeing if the globe updates
(comment
  (rf/dispatch-sync [:globe-cljs.events/add-base-layer "my-first-globe"
                     "Compass" (globe-cljs.worldwind.layer.compass/compass "Compass")])
  (rf/dispatch-sync [:globe-cljs.events/remove-base-layer "my-first-globe" "Compass"])

  (rf/dispatch-sync [:globe-cljs.events/add-base-layer "my-first-globe"
                     "Tesselation" (globe-cljs.worldwind.layer.tesselation/tesselation "Tesselation")])
  (rf/dispatch-sync [:globe-cljs.events/remove-base-layer "my-first-globe" "Tesselation"])

  (rf/dispatch-sync [:globe-cljs.events/add-base-layer "my-first-globe"
                     "Blue Marble" (globe-cljs.worldwind.layer.blue-marble/blue-marble "Blue Marble")])
  (rf/dispatch-sync [:globe-cljs.events/remove-base-layer "my-first-globe" "Blue Marble"])

  ())


; now we can try a more complicated layer: renderable with polygons!
(comment
  (do
    (require '[globe-cljs.layer.renderable :as rl])
    (require '[globe-cljs.surface.polygon :as poly])
    (def children [(poly/polygon [0 0] {:color [255 0 0 1]})
                   (poly/polygon [0 1] {:color [0 255 0 1]})
                   (poly/polygon [1 0] {:color [0 0 255 1]})])
    (def layer (rl/createLayer "polygons" children)))

  (count (.-renderables layer))

  (rf/dispatch-sync [:globe-cljs.events/add-layer "my-first-globe"
                     "polygons" (rl/createLayer
                                  "polygons"
                                  [(poly/polygon [0 0] {:color [255 0 0 1]})
                                   (poly/polygon [8 0] {:color [255 0 0 1]})
                                   (poly/polygon [0 1] {:color [0 255 0 1]})
                                   (poly/polygon [1 0] {:color [0 0 255 1]})])])
  (rf/dispatch-sync [:globe-cljs.events/remove-layer "my-first-globe" "polygons"])
  @re-frame.db/app-db

  ; now some other polygons
  (rf/dispatch-sync [:globe-cljs.events/add-layer
                     "poly-2" (rl/createLayer "poly-2"
                                [(poly/polygon [5 5]
                                   {:color [128 128 0 1]})])])
  (rf/dispatch-sync [:globe-cljs.events/remove-layer "poly-2"])

  ())


