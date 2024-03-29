(ns kokugakuinsumo.views.app
  (:require [reagent.core :as reagent]
            [secretary.core :as secretary :include-macros true]
            [accountant.core :as accountant]
            [re-frame.core :refer [dispatch
                                   subscribe]]
            [kokugakuinsumo.views.components :as c]
            [cljsjs.react-bootstrap]))

(defn header []
  (let [menu [{:name "" :link "#/home" :icon "glyphicon glyphicon-home"}
              {:name "About" :link "#/about"}
              {:name "Member" :link "#/member"}
              {:name "Photo" :link "#/photo"}
              {:name "Record" :link "#/record"}
              {:name "Schedule" :link "#/schedule"}
              #_{:name "Blog" :link "http://ameblo.jp/kokugakuin-sumo/" :outer true}
              {:name "Mail" :link "#/mail"}
              #_{:name "Link" :link "#/linklist"}]]
    [:header
     [:div {:id "title" :class "container"}
      [:a {:to "top" :class "jumbotronLayer"}
       [c/jumbotron {:class "jumbotron"}
        [:h1 "國學院大學相撲部"]
        [:p "kokugakuin university sumo club"]
        [:p {:class "social"}
         [:facebookbutton {:url "{url}" :class "facebook"}
          [:facebookcount {:url "{url}"} ]]
         [:twitterbutton {:url "{url}" :class "twitter"} "-"]]]]]
     [c/nav {:role "navigation" :class "navbar navbar-inverse navbar-custom" :style {:border-radius 0}}
      [:div {:class "container-fluid"}
       [:div {:class "navbar-header"}
        [c/button {:class "navbar-toggle collapsed" :data-toggle "collapse" :data-target "#bs-navbar-collapse" :aria-expanded "false"}
         [:span {:class "sr-only"} "Toggle navigation"]
         [:span {:class "icon-bar"}]
         [:span {:class "icon-bar"}]
         [:span {:class "icon-bar"}]]]
       [:div {:class "collapse navbar-collapse" :id "bs-navbar-collapse"}
        [:ul {:class "nav navbar-nav"}
         (for [m menu]
           (if-not (nil? (:icon m))
             [:li {:class (:style m) :key (:name m)}
              [:a {:href (:link m) :target (when (:outer m) "_blank") :class "hidden-xs"}
               [:i {:class (:icon m)}]]
              [:a {:href (:link m) :target (when (:outer m) "_blank") :class "visible-xs" :data-toggle "collapse" :data-target ".navbar-collapse"}
               [:i {:class (:icon m)}]]]
             [:li {:class (:style m) :key (:name m)}
              [:a {:href (:link m) :target (when (:outer m) "_blank") :class "hidden-xs"} (:name m)]
              [:a {:href (:link m) :target (when (:outer m) "_blank") :class "visible-xs" :data-toggle "collapse" :data-target ".navbar-collapse"} (:name m)]]))]]]]]))

(defn sidebar []
  (reagent/create-class
   {:component-did-mount
    #(js/twttr.widgets.load)

    :reagent-render
    (fn []
      [:div {:id "sidebar"}
       [:aside
        [:h2
         [:span {:id "triangle"} "▼"]"近況"
         [:span {:class "description"} "Information"]]
        [:div {:class "inner", :id "info"}
         "國學院大學相撲同好会は2015年をもって正式に体育会に加盟し、國學院大學相撲部として新たな歴史を刻み始めました。"
         [:br]
         "伝統ある國學院大學で伝統ある相撲という競技をやってみませんか？"
         [:br]
         "選手もマネージャーも募集してます。学年も国籍も問いません。
     また、相撲未経験者も運動をしたことのないという人でも問題ありません。"
         [:br]
         "相撲部で想像出来ないような大学生活を共に送りましょう！"]]
       [:aside
        [:h2
         [:span {:id "triangle"} "▼"]"部員のつぶやき"
         [:span {:class "description"} "Twitter"]]
        [:div {:class "inner"}
         [:a {:class "twitter-timeline" :href "https://twitter.com/kokupyonsumo?ref_src=twsrc%5Etfw"
              :data-height 1000}
          "@kokupyonsumo からのツイート"]]]])}))


(defn footer []
  [:footer {:class "container-fluid"}
   [:small "Copyright 2019-2024 Kokugakuin University Sumo Club. All Rights Reserved."]])

(defn current-page []
  (let [cp         (subscribe [:current-page])
        routes-map (subscribe [:routes])]
    (fn []
      [:div ^{:key @cp}
       [(@routes-map @cp)]])))

(defn app []
  (let [cp         (subscribe [:current-page])
        routes-map (subscribe [:routes])]
    (fn []
      [:div#app
       [header]
       [:div {:class "container main-content"}
        [c/row
         [c/col {:xs 12 :md 8 :id "content-area"}
          [c/css-transition-group {:transitionName "content-area"
                                   :component "div"
                                   :transitionLeaveTimeout 500
                                   :transitionEnterTimeout 500
                                   :transitionLeave false}
           ^{:key @cp}
           [(@routes-map @cp)]]]
         [c/col {:xs 12 :md 4} [sidebar]]]]
       [footer]])))
