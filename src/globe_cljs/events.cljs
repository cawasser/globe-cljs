(ns globe-cljs.events
  (:require
   [re-frame.core :as re-frame]
   [re-frame.db]
   [globe-cljs.db :as db]
   [globe-cljs.subs :as subs]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [taoensso.timbre :as log]))


(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   (log/info ":initialize-db")
   db/default-db))


(re-frame/reg-event-db
  ::init-widget
  (fn-traced [db [_ id]]
    (log/info "init-widget" id)
    (assoc-in db [:widgets id] db/globe-config)))


(defn seconds [t]
  (* t 1000))


(re-frame/reg-event-db
  ::update-time
  (fn-traced [db [_ id new-time]]
    (log/info "update-time" id new-time)
    (assoc-in db [:widgets id :time] new-time)))


(re-frame/reg-event-db
  ::remove-layer
  (fn-traced [db [_ id layer-name]]
    (update-in db [:widgets id :layers] dissoc layer-name)))


(re-frame/reg-event-db
  ::set-projection
  (fn-traced [db [_ id new-projection]]
    (log/info "set-projection" id new-projection)
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


(comment
  (re-frame/dispatch-sync [::toggle-sensor "globe-1" "dummy"])


  (def db @re-frame.db/app-db)
  (def globe-id "globe-1")
  (def s "abi-meso-11")

  (def current-set (get-in db [:widgets globe-id :selected-sensors]))
  (def the-fn (if (contains? current-set s) conj disj))

  (let [current-set (get-in db [:widgets globe-id :selected-sensors])
        the-fn (if (contains? current-set s) disj conj)]
    (update-in db [:widgets globe-id :selected-sensors] the-fn s))




  ())