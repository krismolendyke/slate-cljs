(ns slate-cljs.slate)

(.configAll js/S (js-obj "keyboardLayout" "dvorak"))

(defn bind
  "Bind key to operation."
  [key op]
  (let [modal ":ctrl,alt,cmd,space"]
    (.bind js/S (str key modal) op)))

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

(bind "r" (.op js/S "relaunch"))
(bind "right" (fn [win] (push-right win)))
(bind "left" (fn [win] (push-left win)))
