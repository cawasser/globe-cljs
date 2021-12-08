(ns diagram.node.utils)


(def default-background "#9CA8B3")
(def default-color "#FF")


(def node-style-round {:width "350px" :height "350px"
                       :background default-background
                       :border-radius "50%"
                       :color default-color})


(def node-style-card {:width "250px" :height "350px"})
                      ;:border "solid thin darkgray"
                      ;:border-radius "0.25rem"})


(def node-style-square {:width "100%" :height "100%"
                        :background default-background
                        :color default-color
                        :overflow "hidden"
                        :display :flex
                        :flex-direction :column
                        :justify-content :center
                        :align-items :center})


(def node-style-globe {:width "350px" :height "350px"
                       :border-radius "50%"
                       :overflow "hidden"})


(def image-style {:width "200px" :height "200px"
                  :display :block
                  :margin-left :auto
                  :margin-right :auto})


(def label-style {:margin-top "50px" :margin-bottom "5px"
                  :text-align :center})


(def handle-style {:width "15px" :height "15px" :borderRadius "50%"})