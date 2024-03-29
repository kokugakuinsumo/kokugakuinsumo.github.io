(ns kokugakuinsumo.views.top
  (:require [reagent.core :as reagent]
            [kokugakuinsumo.views.components :as c]
            [cljsjs.react-bootstrap]))

(defn top-page []
  (reagent/create-class
   {:component-did-mount
    (fn []
      (.slick (js/$ ".slick") (clj->js {:accessibility true
                                        :autoplay true
                                        :autoplaySpeed 3000})))

    :reagent-render
    (fn []
      [:div {:id "top"}
       [:h2 "國學院大學相撲部ホームページは移転しました。" [:br]
        "新しい URL は"
        [:a {:href "https://kokugakuinsumo.jimdofree.com/"
             :style {:color "#0000EE" :text-decoration "underline"}} "こちら"]]
       [:br]
       [:br]
       [:div.slick
        [:div [:img {:src "images/top4.jpeg"  :style {:width "100%"}}]]
        [:div [:img {:src "images/top5.jpeg"  :style {:width "100%"}}]]
        [:div [:img {:src "images/top2.jpg"  :style {:width "100%"}}]]
        [:div [:img {:src "images/top1.jpg"  :style {:width "100%"}}]]
        [:div [:img {:src "images/top3.jpg"  :style {:width "100%"}}]]]
       [:row
        [:h1 "新入部員募集中!!"]
        [c/thumbnail "國學院大學相撲部は常に新入部員を募集してます。"
         [:br]
         "「大学で何かやってみたい」と考えて入学してきたもののどのサークルも物足りない。\n
          そんなあなたに足りないものがここにある…かも知れません。\n
          今、相撲部に入部すると下記の特典があります！"
         [:h3 "入部特典"]
         [:ul
          [:li "廻し贈呈 "
           [:p "文字通り裸一貫で始められます"]]
          [:li "国技館の土俵に立つ権利 "
           [:p "東日本インカレに参加することで必ず立てます"]]
          [:li "大相撲力士との交流 "
           [:p "複数の部屋の稽古や合宿に参加しています"]]
          [:li "逞しい肉体 "
           [:p "見違えられます"]]]"新入生も新入生以外の人も留学生もマネージャー希望の人も歓迎します。"
         [:br]"見学・体験・入部希望の方は "
         [:a {:href "/#/mail"} "gmailアドレス"]" までご連絡下さい。"]
        [:h1 "稽古時間・場所"]
        [c/thumbnail
         [:p "現在(2023/03)、下記の内容で稽古を行っています。"]
         [:p "※ 長期休暇中は月曜、水曜 10:00‐"]
         [:table
          [:colgroup
           [:col {:width "7%"}]
           [:col {:width "8%"}]
           [:col {:width "35%"}]
           [:col {:width "50%"}]]
          [:thead
           [:tr
            [:th "曜日"]
            [:th "時間"]
            [:th "活動内容"]
            [:th "場所"]]]
          [:tbody
           [:tr
            [:td {:align "center"} "月曜"]
            [:td "18:00 -"]
            [:td "稽古"]
            [:td "東京大学駒場キャンパス 相撲場"]]
           [:tr
            [:td {:align "center"} "水曜"]
            [:td "18:00 -"]
            [:td "稽古"]
            [:td "東京大学駒場キャンパス 相撲場"]]
           [:tr
            [:td {:align "center"} "土曜"]
            [:td "10:00 -"]
            [:td "稽古"]
            [:td "東京大学駒場キャンパス 相撲場"]]]]]]])}))
