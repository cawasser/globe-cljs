(ns globe-cljs.reagent-context
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [react :as react]))



(defonce my-context (react/createContext "default"))

(def Provider (.-Provider my-context))
(def Consumer (.-Consumer my-context))

(defn children [props]
  [:> Consumer
   (fn [context]
     (r/as-element [:div props "Context: " (pr-str context)]))])

(defn root []
  ;; Provider takes props with single property, value
  ;; :< or adapt-react-class converts the Cljs properties
  ;; map to JS object for the Provider component.
  [:div
   [:> Provider {:value "bar"}
    [children {:style {:background-color :lightgreen}}]]

   ;; :> and adapt-react-class convert the props
   ;; recursively to JS objects, so this might not be
   ;; what you want.
   [:> Provider {:value {:foo "bar"}}
    [children {}]]

   ;; To yourself control how the props are handled,
   ;; use create-element directly.
   ;; Properties map needs to be JS object here, but now the
   ;; value is used as is, Cljs map.
   ;; Note that you need to convert children from Reagent markup
   ;; to React elements yourself.
   (r/create-element Provider
     #js {:value {:foo "bar"}}
     (r/as-element [children {:style {:background-color :lightpink}}]))])