(ns globe-cljs.events
  (:require
   [re-frame.core :as re-frame]
   [globe-cljs.db :as db]
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
  ::toggle-timer
  (fn-traced [db [_ id]]
    (log/info "toggle-timer" id)
    (if-let [timer (get-in db [:widgets id :timer])]
      (do
        (log/info "stopping" id)
        (js/clearInterval timer)
        (assoc-in db [:widgets id :timer] nil))
      (do
        (log/info "starting" id)
        (assoc-in db [:widgets id :timer] (js/setInterval #(re-frame/dispatch-sync [::move-cell])
                                            (seconds 0.5)))))))


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
  ::move-cell
  (fn-traced [db [_ id]]
    (log/info "::move-cell from" id (get-in db [:widgets id :current-cell]))
    (let [[current-r current-c] (get-in db [:widgets id :current-cell])
          next-c (inc current-c)]
      (if (> next-c 9)
        (let [new-r (mod (inc current-r) 10)
              new-c 0]
          (assoc-in db [:widgets id :current-cell] [new-r new-c]))
        (assoc-in db [:widgets id :current-cell] [current-r next-c])))))


(comment
  @re-frame.db/app-db

  (re-frame/dispatch-sync [::move-cell "my-first-globe"])

  (def test-t {:timer nil})
  (if-let [timer (:timer test-t)]
    true false)
  ())