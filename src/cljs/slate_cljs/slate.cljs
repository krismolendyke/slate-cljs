(ns slate-cljs.slate)

(defn bind
  "Bind key to operation."
  [key op]
  (let [modal ":ctrl,alt,cmd,space"]
    (.bind js/S (str key modal) op)))

(defn bind-win
  [key win-op]
  (let [modal ":ctrl,alt,cmd,space"]
    (.bind js/S (str key modal) (fn [win] (win-op win)))))

(defn push
  [win direction style]
  (.doOperation win (.op js/S "push" (js-obj "direction" direction
                                             "style" style))))

(defn push-right
  [win]
  (push win "right" "bar-resize:screenSizeX/1.7"))

(defn push-left
  [win]
  (push win "left" "bar-resize:screenSizeX/2.5"))

(defn move-center
  [win]
  (.doOperation win (.op js/S "move"
                         (js-obj "x" "(screenSizeX / 2) - (windowSizeX / 2)"
                                 "y" "screenOriginY"
                                 "width" "windowSizeX"
                                 "height" "windowSizeY"))))

(.configAll js/S (js-obj "keyboardLayout" "dvorak"))

(bind "r" (.op js/S "relaunch"))

(bind-win "right" push-right)
(bind-win "left" push-left)
(bind-win "c" move-center)
