(ns globe-cljs.subs
  (:require
    [re-frame.core :as re-frame]
    [re-frame.db :as rdb]
    [taoensso.timbre :as log]

    [globe.worldwind.defaults :as defaults]))


(re-frame/reg-sub
  ::name
  (fn [db]
    (:name db)))


(re-frame/reg-sub
  ::base-layers
  (fn [db [_ id]]
    (or (get-in db [:widgets id :base-layers]) [])))


(re-frame/reg-sub
  ::projection
  (fn [db [_ id]]
    (or (get-in db [:widgets id :projection]) defaults/projection)))


(re-frame/reg-sub
  ::selected-sensors
  (fn [db [_ id]]
    (or (get-in db [:widgets id :selected-sensors]) #{})))


(re-frame/reg-sub
  ::selected-aois
  (fn [db [_ id]]
    (or (get-in db [:widgets id :selected-aois]) #{})))


(re-frame/reg-sub
  ::current-sensor-cells

  (fn [[_ id] _]
    [(re-frame/subscribe [::time id])
     (re-frame/subscribe [::selected-sensors id])
     (re-frame/subscribe [::sensor-allocations])])

  (fn [[time selected-sensors sensor-allocations] [_ id]]
    (if-let [cells (->> sensor-allocations
                     (filter #(= time (:time %)))
                     (map (juxt :cell :coverage))
                     (mapcat (fn [[cell coverages]]
                               (for [c coverages]
                                 {cell (:sensor c)})))
                     (filter #(contains? selected-sensors (first (vals %)))))]
      (do
        ;(log/info "::current-sensor-cells" cells)
        cells)
      (do
        ;(log/info "::current-sensor-cells returning []")
        []))))


(re-frame/reg-sub
  ::current-aoi-cells

  (fn [[_ id] _]
    [(re-frame/subscribe [::time id])
     (re-frame/subscribe [::selected-aois id])
     (re-frame/subscribe [::aois])])

  (fn [[time-t selected-aois aois] [_ id]]
    (if-let [cells (->> aois
                     (filter (fn [[k v]] (contains? selected-aois k)))
                     vals
                     (map (juxt :cells :symbol))
                     (map (fn [[cells [_ symbol]]]
                            (map (fn [[row col _ t]]
                                   [t [row col symbol]])
                              cells)))
                     first
                     (filter (fn [[t cell]] (= time-t t)))
                     (map (fn [[t cells]] cells)))]
      (do
        (log/info "::current-aoi-cells" cells)
        cells)
      (do
        (log/info "::current-aoi-cells returning []")
        []))))


(re-frame/reg-sub
  ::time
  (fn [db [_ id]]
    (or (get-in db [:widgets id :time]) 0)))


(re-frame/reg-sub
  ::sensor-types
  (fn [db _]
    (->> db
      :sensor-allocations
      (map :coverage)
      (mapcat #(map :sensor %))
      set)))


(re-frame/reg-sub
  ::sensor-allocations
  (fn [db _]
    (or (:sensor-allocations db) [])))


(re-frame/reg-sub
  ::aois
  (fn [db _]
    (or (:aois db) [])))


(re-frame/reg-sub
  ::diagram-elements
  (fn [db _]
    (or (:diagram-elements db) [])))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;
; RICH COMMENTS
;


; work out filtering for the selected aois, so we can generate cells form them
(comment
  ; goal is to produce:

  [[0 0 "/image/some-symbol.png"]
   [4 5 "/image/some-symbol.png"]
   [2 5 "/image/some-symbol.png"]]

  (do
    (def id "globe-1")
    (def time-t @(re-frame/subscribe [::time id]))
    (def selected-aois @(re-frame/subscribe [::selected-aois id]))
    (def aois @(re-frame/subscribe [::aois])))



  (->> aois
    (filter (fn [[k v]] (contains? selected-aois k)))
    vals
    (map (juxt :cells :symbol)))

  ;    produces:
  ; first  partial transformation
  (def p-1 '([#{[5 4 "hidef-image" 1] [5 3 "hidef-image" 2] [5 5 "hidef-image" 0]}
              [#js{:prefix "fas",
                   :iconName "umbrella",
                   :icon #js[576
                             512
                             #js[]
                             "f0e9"
                             "M575.7 280.8C547.1 144.5 437.3 62.6 320 49.9V32c0-17.7-14.3-32-32-32s-32 14.3-32 32v17.9C138.3 62.6 29.5 144.5.3 280.8c-2.2 10.1 8.5 21.3 18.7 11.4 52-55 107.7-52.4 158.6 37 5.3 9.5 14.9 8.6 19.7 0 20.2-35.4 44.9-73.2 90.7-73.2 58.5 0 88.2 68.8 90.7 73.2 4.8 8.6 14.4 9.5 19.7 0 51-89.5 107.1-91.4 158.6-37 10.3 10 20.9-1.3 18.7-11.4zM256 301.7V432c0 8.8-7.2 16-16 16-7.8 0-13.2-5.3-15.1-10.7-5.9-16.7-24.1-25.4-40.8-19.5-16.7 5.9-25.4 24.2-19.5 40.8 11.2 31.9 41.6 53.3 75.4 53.3 44.1 0 80-35.9 80-80V301.6c-9.1-7.9-19.8-13.6-32-13.6-12.3.1-22.4 4.8-32 13.7z"]}
               "images/symbols/cloud-rain-solid.png"]]))


  (map (fn [[cells [_ symbol]]]
          [cells symbol])
    p-1)

  ;    produces:
  ; next partial transformation
  (def p-2 '([#{[5 4 "hidef-image" 1] [5 3 "hidef-image" 2] [5 5 "hidef-image" 0]}
              "images/symbols/cloud-rain-solid.png"]))

  (map (fn [[row col _ t]]
         [t [row col]])
    p-2)

  ; combine the partials:
  (map (fn [[cells [_ symbol]]]
         (map (fn [[row col _ t]]
                [t [row col symbol]])
           cells))
    p-1)


  ; now we just need to filter out the correct time-t's

  (def p-3 '([1 [5 4 "images/symbols/cloud-rain-solid.png"]]
             [2 [5 3 "images/symbols/cloud-rain-solid.png"]]
             [0 [5 5 "images/symbols/cloud-rain-solid.png"]]))

  (filter (fn [[t cell]] (= time-t t))
    p-3)

  ; and grab just the "cells" part of each result
  (->> p-3
    (filter (fn [[t cell]] (= time-t t)))
    (mapcat (fn [[t cells]] cells)))

  ; ok, can we pull it all together?
  (def selected-aois #{"alpha-hd"})
  (->> aois
    (filter (fn [[k v]] (contains? selected-aois k)))
    vals
    (map (juxt :cells :symbol))
    (map (fn [[cells [_ symbol]]]
           (map (fn [[row col _ t]]
                  [t [row col symbol]])
             cells)))
    first
    (filter (fn [[t cell]] (= time-t t)))
    (map (fn [[t cells]] cells)))


  (map (fn [[cells [_ symbol]]]
           (map (fn [[row col _ t]]
                  [t [row col symbol]])
             cells)) p-1)

  ())