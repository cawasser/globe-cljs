(ns globe-cljs.subs
  (:require
   [re-frame.core :as re-frame]))

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
  ::current-cell
  (fn [db [_ id]]
    (get-in db [:widgets id :current-cell])))


(re-frame/reg-sub
  ::timer
  (fn [db [_ id]]
    (get-in db [:widgets id :timer])))
