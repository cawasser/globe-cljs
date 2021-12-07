(ns globe-cljs.globe-component
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [globe-cljs.subs :as subs]
    [globe-cljs.events :as events]
    [taoensso.timbre :as log]

    [globe-cljs.sensor-data :as sd]

    [cljs-time.core :as cljs-time]
    [cljs-time.coerce :as coerce]
    [globe.globe :as g]
    [globe-cljs.cell.layer-support :as ls]

    ["@fortawesome/react-fontawesome" :refer (FontAwesomeIcon)]))


(defn- time-slider [id current-time-t]
  [:div {:style {:display     :flex
                 :align-items :center
                 :width       "50%"}}

   [:div.slidecontainer
    [:input#myRange.slider
     {:style     {:width 400 :margin-right "40px"}
      :type      "range" :min "0" :max "9" :value @current-time-t
      :on-change #(do
                    ;(log/info "time-slider" (js/parseInt (-> % .-target .-value)))
                    (re-frame/dispatch-sync [::events/update-time id (js/parseInt (-> % .-target .-value))]))}]]

   [:h3 (str (coerce/to-date
               (cljs-time/plus ls/starting-date-time
                 (cljs-time/hours (or @current-time-t 0)))))]])


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


(defn- aoi-visibility-control [globe-id aois selected-aois]

  (log/info "aoi-visibility-control" @aois @selected-aois)

  [:div {:style {:display        :flex
                 :flex-direction :row}}
   (doall
     (for [[aoi attribs] @aois]
       ^{:key aoi}
       [:div {:style {:margin  "5px"
                      :padding "5px"}}
        [:input {:type     "checkbox"
                 :value    (contains? @selected-aois aoi)
                 :on-click #(re-frame/dispatch-sync
                              [::events/toggle-aoi globe-id aoi])}]
        [:span.icon-text
         [:span.icon [:> FontAwesomeIcon {:icon (first (:symbol attribs))}]]
         [:span aoi]]]))])


(defn globe [globe-id]
  (let [sensors          (re-frame/subscribe [::subs/sensor-types])
        selected-sensors (re-frame/subscribe [::subs/selected-sensors])
        aois             (re-frame/subscribe [::subs/aois])
        selected-aois    (re-frame/subscribe [::subs/selected-aois])
        colors           (ls/get-sensor-colors @sensors)
        base-layer       (re-frame/subscribe [::subs/base-layers globe-id])
        layers           (re-frame/subscribe [::subs/layers globe-id colors])
        projection       (re-frame/subscribe [::subs/projection globe-id])
        time-t           (re-frame/subscribe [::subs/time globe-id])]

    (re-frame/dispatch-sync [::events/init-widget globe-id])

    (fn []
      [:div {:style {:padding      "20px" :border-width "3px"
                     :border-style :solid :border-color :black}}
       [:div
        [:h1 globe-id]

        [time-slider globe-id time-t]

        [:div
         [sensor-visibility-control globe-id sensors selected-sensors colors]
         [aoi-visibility-control globe-id aois selected-aois]
         [projection-control globe-id projection]]]

       [:div {:style {:width "50%"}}
        [g/globe {:id         globe-id
                  :min-max    :max
                  :time       (coerce/to-date
                                (cljs-time/plus ls/starting-date-time
                                  (cljs-time/hours (or @time-t 0))))
                  :projection (or @projection "3D")
                  :style      {:background-color :black
                               :width            "100%"
                               :height           "100%"}}
         (merge @base-layer @layers)]]])))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; RICH COMMENTS
;

; set up the colors for the sensors
(comment
  (def sensors (re-frame/subscribe [::subs/sensor-types]))
  (def colors (ls/get-sensor-colors @sensors))

  (def sensor-allocs @(re-frame/subscribe [::subs/sensor-allocations]))


  ())


; try updating the app-db and seeing if the globe updates
(comment
  (rf/dispatch-sync [:globe-cljs.events/add-base-layer "my-first-globe"
                     "Compass" (globe.worldwind.layer.compass/compass "Compass")])
  (rf/dispatch-sync [:globe-cljs.events/remove-base-layer "my-first-globe" "Compass"])

  (rf/dispatch-sync [:globe-cljs.events/add-base-layer "my-first-globe"
                     "Tesselation" (globe.worldwind.layer.tesselation/tesselation "Tesselation")])
  (rf/dispatch-sync [:globe-cljs.events/remove-base-layer "my-first-globe" "Tesselation"])

  (rf/dispatch-sync [:globe-cljs.events/add-base-layer "my-first-globe"
                     "Blue Marble" (globe.worldwind.layer.blue-marble/blue-marble "Blue Marble")])
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


; work out how to make the time-slider change the day/night viz
(comment
  (def time-t (atom nil))
  (cljs-time/plus ls/starting-date-time (cljs-time/hours (or @time-t 0)))

  (cljs-time/to-long ls/starting-date-time)
  (js/Date.)

  (inst/with-out-str ls/starting-date-time)

  (= (with-out-str (pr ls/starting-date-time))
    "#inst \"2017-01-01\"")

  (coerce/to-date ls/starting-date-time)

  (coerce/to-date
    (cljs-time/plus ls/starting-date-time
      (cljs-time/hours (or @time-t 0))))

  ())


; can we do (consistent) renderables with surface-image?
(comment


  ())


; lets get crazy with the nodes
(comment
  (re-frame/dispatch [::events/add-element {:id       "500"
                                            :el-type  :node
                                            :data     {:label (reagent/as-element [globe "globe-2"])}
                                            :position {:x 300 :y 125}}])


  ())
