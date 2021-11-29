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


(defn seconds [t]
  (* t 1000))


(re-frame/reg-event-db
  ::toggle-timer
  (fn-traced [db _]
    (log/info "toggle-timer")
    (if-let [timer (:timer db)]
      (do
        (log/info "stopping")
        (js/clearInterval timer)
        (assoc db :timer nil))
      (do
        (log/info "starting")
        (assoc db :timer (js/setInterval #(re-frame/dispatch-sync [::move-cell])
                           (seconds 0.5)))))))


(re-frame/reg-event-db
  ::remove-layer
  (fn-traced [db [_ layer-name]]
    (update db :layers dissoc layer-name)))


(re-frame/reg-event-db
  ::add-base-layer
  (fn-traced [db [_ layer-name layer]]
    (update db :base-layers merge {layer-name layer})))


(re-frame/reg-event-db
  ::remove-base-layer
  (fn-traced [db [_ layer-name]]
    (update db :base-layers dissoc layer-name)))


(re-frame/reg-event-db
  ::set-layers
  (fn-traced [db [_ layer-name layer]]
    (-> db
      (dissoc :layers) ; remove all existing layers
      (assoc :layers {layer-name layer}))))


(re-frame/reg-event-db
  ::move-cell
  (fn-traced [db _]
    (log/info "::move-cell from" (:current-cell db))
    (let [[current-r current-c] (:current-cell db)
          next-c (inc current-c)]
      (if (> next-c 9)
        (let [new-r (mod (inc current-r) 10)
              new-c 0]
          (assoc db :current-cell [new-r new-c]))
        (assoc db :current-cell [current-r next-c])))))


(comment
  @re-frame.db/app-db

  (re-frame/dispatch-sync [::move-cell])

  (def test-t {:timer nil})
  (if-let [timer (:timer test-t)]
    true false)
  ())