(ns kokugakuinsumo.views.mail)

(defn mail-page []
  [:div {:id "mail"}
   [:row
    [:iframe {:src "https://ws.formzu.net/fgen/S827135345/"
              :style {:height "600px" :max-width "600px" :width "100%" :border 0}}]]])
