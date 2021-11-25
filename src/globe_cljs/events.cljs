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
  ::remove-layer
  (fn-traced [db [_ layer-name]]
    (update db :layers dissoc layer-name)))


(re-frame/reg-event-db
  ::add-layer
  (fn-traced [db [_ layer-name layer]]
    (update db :layers merge {layer-name layer})))

