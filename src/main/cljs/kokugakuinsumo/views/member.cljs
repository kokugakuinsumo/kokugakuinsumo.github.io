(ns kokugakuinsumo.views.member
  (:require [reagent.core :refer [create-class]]
            [re-frame.core :as r]
            [kokugakuinsumo.views.components :as c]))

(defn member [member]
  [:div {:class "element"}
   [c/row
    [c/col {:xs 6 :md 6}
     [c/thumbnail {:src (:image member)}]]
    [c/col {:xs 6 :md 6 :class "caption"}
     [:h3
      [:span {:class "name"} (:name member)]
      [:span {:class "yomi"} (:pronunciation member)]]
     [:h4 "身長/体重"]
     (str (:height member) " / " (:weight member))
     (when (:introductiontitle member)
       [:h4 (:introductiontitle member)])
     (when (:introductioncontent member)
       (for [r (-> member
                   :introductioncontent
                   (clojure.string/split #"\\n"))]
         [:div {:key r} [:span r] [:br]]))
     [:p {:class "toTop", :on-click #(.scrollIntoView
                                      (js/document.getElementById "memberHeader")
                                      true)} "部員一覧へ戻る"]]]])

(defn member-list [members]
  [:table {:id "memberHeader"}
   [:thead
    [:tr
     [:th "学年"]
     [:th "役職"]
     [:th "名前"]
     [:th "所属"]
     [:th "出身地"]]]
   [:tbody
    (for [m members]
      [:tr {:class "fromMember" :key (:id m) :on-click #(.scrollIntoView
                                                         (js/document.getElementById (:id m))
                                                         true)}
       [:td (:grade m)]
       [:td (:post m)]
       [:td (:name m)]
       [:td (:affiliation m)]
       [:td (:birthplace m)]])]])

(defn member-page []
  (create-class
   {:component-will-mount
    (fn []
      (r/dispatch [:get-members]))
    :reagent-render
    (fn []
      (let [members @(r/subscribe [:members])]
        [:div {:id "member"}
         [c/row
          [c/col {:xs 12 :md 12}
           [:h1 "部員紹介"]]
          (when-not (nil? members)
            [:div
             [c/col {:xs 12 :md 12}
              [member-list members]]
             [c/col {:xs 12 :md 12 :id "memberBody"}
              (for [m members]
                [:div {:key (:id m) :id (:id m)}
                 [member m]])]])]]))}))
