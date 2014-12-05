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
  (push win "right" "bar-resize:screenSizeX / 3"))

(defn push-left
  [win]
  (push win "left" "bar-resize:screenSizeX / 3"))

(defn move-center
  [win]
  (.doOperation win (.op js/S "move"
                         (clj->js {:x "(screenSizeX / 3)"
                                   :y "screenOriginY"
                                   :width "(screenSizeX / 3)"
                                   :height "windowSizeY"}))))

(defn grid
  [win]
  (let [grids (clj->js
               {:grids {:1366x768  {:width 6 :height 2}
                        :1680x1050 {:width 8 :height 2}
                        :1920x1080 {:width 8 :height 2}
                        :2560x1440 {:width 4 :height 4}}})]
    (.doOperation win (.op js/S "grid" grids))))

(.log js/S (-> js/S .screen .isMain))

(.configAll js/S (js-obj "keyboardLayout" "dvorak"))

(bind "r" (.op js/S "relaunch"))

(bind-win "right" push-right)
(bind-win "left" push-left)
(bind-win "c" move-center)
(bind-win "g" grid)
