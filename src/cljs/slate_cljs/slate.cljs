(ns slate-cljs.slate)

(def MODAL ":ctrl,alt,cmd,space")

(.configAll js/S (js-obj "keyboardLayout" "dvorak"))

(.bind js/S (+ "r" MODAL) (.op js/S "relaunch"))
