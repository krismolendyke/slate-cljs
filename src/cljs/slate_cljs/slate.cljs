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

(.configAll js/S (js-obj "keyboardLayout" "dvorak"))

(bind "r" (.op js/S "relaunch"))

(bind-win "right" push-right)
(bind-win "left" push-left)
