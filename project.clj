(defproject slate-cljs "0.1.0-SNAPSHOT"
  :description "Slate window management configuration."
  :url "https://github.com/jigish/slate"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371"]]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :cljsbuild {:builds
              [{
                :source-paths ["src/cljs"]
                :compiler {
                           :output-to "slate.js"
                           :optimizations :whitespace
                           :pretty-print true}}]})
