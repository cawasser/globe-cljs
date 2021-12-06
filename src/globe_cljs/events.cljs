(ns globe-cljs.events
  (:require
   [re-frame.core :as re-frame]
   [re-frame.db]
   [globe-cljs.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [taoensso.timbre :as log]))


(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   ;(log/info ":initialize-db")
   db/default-db))


(re-frame/reg-event-db
  ::init-widget
  (fn-traced [db [_ id]]
    ;(log/info "init-widget" id)
    (assoc-in db [:widgets id] (db/globe-config id))))


(re-frame/reg-event-db
  ::update-time
  (fn-traced [db [_ id new-time]]
    ;(log/info "update-time" id new-time)
    (assoc-in db [:widgets id :time] new-time)))


(re-frame/reg-event-db
  ::remove-layer
  (fn-traced [db [_ id layer-name]]
    (update-in db [:widgets id :layers] dissoc layer-name)))


(re-frame/reg-event-db
  ::set-projection
  (fn-traced [db [_ id new-projection]]
    ;(log/info "set-projection" id new-projection)
    (assoc-in db [:widgets id :projection] new-projection)))


(re-frame/reg-event-db
  ::add-base-layer
  (fn-traced [db [_ id layer-name layer]]
    (update-in db [:widgets id :base-layers] merge {layer-name layer})))


(re-frame/reg-event-db
  ::remove-base-layer
  (fn-traced [db [_ id layer-name]]
    (update-in db [:widgets id :base-layers] dissoc layer-name)))


(re-frame/reg-event-db
  ::add-layer
  (fn-traced [db [_ id layer-name layer]]
    (update-in db [:widgets id :layers] merge {layer-name layer})))


(re-frame/reg-event-db
  ::remove-layer
  (fn-traced [db [_ id layer-name]]
    (update-in db [:widgets id :layers] dissoc layer-name)))


(re-frame/reg-event-db
  ::set-layers
  (fn-traced [db [_ id layer-name layer]]
    (-> db
      (update-in [:widgets id :layers] dissoc layer-name) ; remove all existing layers
      (assoc-in [:widgets id :layers] {layer-name layer}))))


(re-frame/reg-event-db
  ::toggle-sensor

  (fn-traced [db [_ globe-id selection]]
    (let [current-set (get-in db [:widgets globe-id :selected-sensors])
          the-fn (if (contains? current-set selection) disj conj)]
      (update-in db [:widgets globe-id :selected-sensors] the-fn selection))))


(re-frame/reg-event-db
  ::toggle-aoi

  (fn-traced [db [_ globe-id selection]]
    (let [current-set (get-in db [:widgets globe-id :selected-aois])
          the-fn (if (contains? current-set selection) disj conj)]
      (update-in db [:widgets globe-id :selected-aois] the-fn selection))))


(re-frame/reg-event-db
  ::add-element

  (fn-traced [db [_ new-element]]
    (update db :diagram-elements conj new-element)))


(re-frame/reg-event-db
  ::remove-element

  (fn-traced [db [_ element-id]]
    (let [current (:diagram-elements db)]
      (assoc db :diagram-elements (remove (fn [x]
                                            (= (:id x) element-id))
                                    current)))))


(re-frame/reg-event-db
  ::update-element

  (fn-traced [db [_ {:keys [id] :as element}]]
    (let [current (:diagram-elements db)]
      (-> db
        (assoc :diagram-elements (remove (fn [x]
                                           (= (:id x) id))
                                   current))
        (update :diagram-elements conj element)))))


; removing an element from a vector with a given "map key"
(comment

  (def current {:diagram-elements [{:id :a} {:id :b} {:id :c}]
                :dummy "this should stay"})
  (def element-id :b)

  (update current :diagram-elements remove (fn [x] (= (:id x) element-id)))

  (assoc current :diagram-elements (remove (fn [x]
                                             (= (:id x) element-id))
                                     (:diagram-elements current)))

  ())

