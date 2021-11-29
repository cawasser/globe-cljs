(ns globe-cljs.core
  (:require
    [reagent.dom :as rdom]
    [re-frame.core :as re-frame]
    [globe-cljs.events :as events]
    [globe-cljs.views :as views]
    [globe-cljs.config :as config]
    [taoensso.timbre :as log]))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))


(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))


(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root)
  (re-frame/dispatch-sync [::events/start-timer]))
