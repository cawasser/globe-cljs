(ns globe-cljs.subs
  (:require
    [re-frame.core :as re-frame]
    [re-frame.db :as rdb]
    [taoensso.timbre :as log]))


(re-frame/reg-sub
  ::name
  (fn [db]
    (:name db)))


(re-frame/reg-sub
  ::base-layers
  (fn [db [_ id]]
    (get-in db [:widgets id :base-layers])))


(re-frame/reg-sub
  ::projection
  (fn [db [_ id]]
    (get-in db [:widgets id :projection])))


(re-frame/reg-sub
  ::selected-sensors
  (fn [db [_ id]]
    (get-in db [:widgets id :selected-sensors])))


(re-frame/reg-sub
  ::current-cells

  (fn [[_ id] _]
    [(re-frame/subscribe [::time id])
     (re-frame/subscribe [::selected-sensors id])
     (re-frame/subscribe [::sensor-allocations])])

  (fn [[time selected-sensors sensor-allocations] [_ id]]
    (let [cells (->> sensor-allocations
                  (filter #(= time (:time %)))
                  (map (juxt :cell :coverage))
                  (mapcat (fn [[cell coverages]]
                            (for [c coverages]
                              {cell (:sensor c)})))
                  (filter #(contains? selected-sensors (first (vals %)))))]
      (log/info "::current-cells" cells)
      cells)))


(re-frame/reg-sub
  ::time
  (fn [db [_ id]]
    (get-in db [:widgets id :time])))


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
    (:sensor-allocations db))

 ())