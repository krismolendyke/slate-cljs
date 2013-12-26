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

(defn push-right
  [win]
  (let [args (js-obj "direction" "right"
                     "style" "bar-resize:screenSizeX/1.7")]
    (.doOperation win (.op js/S "push" args))))

(defn push-left
  [win]
  (let [args (js-obj "direction" "left"
                     "style" "bar-resize:screenSizeX/2.5")]
    (.doOperation win (.op js/S "push" args))))

(.configAll js/S (js-obj "keyboardLayout" "dvorak"))

(bind "r" (.op js/S "relaunch"))

(bind-win "right" push-right)
(bind-win "left" push-left)
