(ns slate-cljs.slate)

(defn key->modal
  "Append the modal key to the given key."
  [key]
  (str key ":ctrl,alt,cmd,space"))

(defn bind
  "Bind key to operation."
  [key op]
  (.bind js/S (key->modal key) op))

(defn bind-win
  "Bind key to window operation."
  [key win-op]
  (.bind js/S (key->modal key) (fn [win] (win-op win))))

(defn push
  [win direction style]
  (let [args (clj->js {:direction direction :style style})]
    (.doOperation win (.op js/S "push" args))))

(defn push-right
  [win]
  (push win "right" "bar-resize:screenSizeX / 1.7"))

(defn push-left
  [win]
  (push win "left" "bar-resize:screenSizeX / 2.5"))

(defn move-center
  [win]
  (let [width "screenSizeX * 2/3"
        x (str "(screenSizeX / 2) - ((" width ") / 2)")
        args (clj->js {:x x
                       :y "screenOriginY"
                       :width width
                       :height "windowSizeY"})]
    (.doOperation win (.op js/S "move" args))))

(defn grid
  [win]
  (let [grids (clj->js
               {:grids {:1366x768 {:width 6 :height 2}
                        :1680x1050 {:width 8 :height 2}}})]
    (.doOperation win (.op js/S "grid" grids))))

(.configAll js/S (js-obj "keyboardLayout" "dvorak"))

(bind "r" (.op js/S "relaunch"))

(bind-win "right" push-right)
(bind-win "left" push-left)
(bind-win "c" move-center)
(bind-win "g" grid)
