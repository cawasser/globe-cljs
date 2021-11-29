(ns globe-cljs.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))


(re-frame/reg-sub
  ::base-layer
  (fn [db]
    (:base-layer db)))


(re-frame/reg-sub
  ::current-cell
  (fn [db]
    (:current-cell db)))


(re-frame/reg-sub
  ::timer
  (fn [db]
    (:timer db)))
