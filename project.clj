(defproject chess "1.0.0"
  :description "generate random chess board"
  :url "https://github.com/kcpyeung/chess"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/math.combinatorics "0.1.5"]]
  :repl-options {:init-ns chess.core}
  :main chess.core)
