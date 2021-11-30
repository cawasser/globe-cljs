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


(defn- five-cells [[row col]]
  (let [above (mod (- row 1) 10)
        below (mod (+ row 1) 10)
        left (mod (- col 1) 10)
        right (mod (+ col 1) 10)]
    [[row col] [above col] [below col] [row left] [row right]]))

(re-frame/reg-sub
  ::current-cells

  (fn [[_ id] _]
    (re-frame/subscribe [::timer id]))

  (fn [timer [_ id]]
    (let [row (Math/floor (/ timer 10))
          col (mod timer 10)]
      (five-cells [row col]))))


(comment
  (Math/floor (/ 3 10))


  (dec (mod 3 10))
  (dec (mod 13 10))
  (dec (mod 23 10))

  (def timer 0)
  (let [row (Math/floor (/ timer 10))
        col (mod timer 10)]
    {:r row :c col})

  (def row 0)
  (def col 3)
  (mod (- row 1) 10)
  (mod (+ row 1) 10)

  (let [above (mod (- row 1) 10)
        below (mod (+ row 1) 10)
        left (mod (- col 1) 10)
        right (mod (+ col 1) 10)]
    [[row col] [above col] [below col] [row left] [row right]])

  ())

(re-frame/reg-sub
  ::timer
  (fn [db [_ id]]
    (get-in db [:widgets id :timer])))
