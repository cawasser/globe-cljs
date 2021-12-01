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


(defn- five-cells [[row col]]
  (let [above (mod (- row 1) 10)
        below (mod (+ row 1) 10)
        left  (mod (- col 1) 10)
        right (mod (+ col 1) 10)]
    [[row col] [above col] [below col] [row left] [row right]]))


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


(comment
  (do
    (def id "globe-1")
    (def time-t @(re-frame/subscribe [::time id]))
    (def selected-sensors @(re-frame/subscribe [::selected-sensors id]))
    (def sensor-allocations @(re-frame/subscribe [::sensor-allocations])))

  (def sample [{:time        0,
                :cell        [9 6],
                :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                :computed_at "2021-08-02T15:16:05.558813"}
               {:time        0,
                :cell        [9 7],
                :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                :computed_at "2021-08-02T15:16:05.558813"}])

  (->> sample
    (map (juxt :cell :coverage)))


  (def sample-2 [[[9 0] #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}}]
                 [[9 1]
                  #{{:platform "goes-west", :sensor "abi-3"}
                    {:platform "metop-yy", :sensor "avhhr-6"}
                    {:platform "goes-east", :sensor "abi-1"}}]])
  (map (fn [[cell coverages]]
         (for [c coverages]
           {cell (:sensor c)})) sample-2)


  (def sample-3 '({[0 0] "abi-3"}
                  {[0 0] "abi-1"}
                  {[0 1] "abi-3"}
                  {[0 1] "avhhr-6"}
                  {[0 1] "abi-1"}
                  {[0 2] "abi-3"}
                  {[0 2] "avhhr-6"}))

  (def selected-sensors #{"avhhr-6"})

  (contains? selected-sensors (first (vals {[0 0] "abi-3"})))


  (filter #(contains? selected-sensors (first (vals %))) sample-3)

  (->> sensor-allocations
    (filter #(= time-t (:time %)))
    (map (juxt :cell :coverage))
    (mapcat (fn [[cell coverages]]
              (for [c coverages]
                {cell (:sensor c)})))
    (filter #(contains? selected-sensors (first (vals %)))))

  @(re-frame/subscribe [::current-cells id])

  ())


;(let [row (Math/floor (/ timer 10))
;      col (mod timer 10)]
;  (five-cells [row col]))))


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
    (:sensor-allocations db)))


(comment
  (:sensor-allocations @re-frame.db/app-db)

  (def time 0)
  (->> @re-frame.db/app-db
    :sensor-allocations
    (filter #(= time (:time %))))

  @(re-frame/subscribe [::time "globe-1"])
  @(re-frame/subscribe [::time "globe-2"])

  @(re-frame/subscribe [::sensor-allocations "globe-1"])

  ())