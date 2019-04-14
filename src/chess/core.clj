(ns chess.core
  (:require [chess.board :refer [board]])
  (:require [chess.printer :refer [print-board]])
  (:gen-class))

(defn- flag [& args]
  (let [f (first args)]
    (cond
      (nil? f)          :fen
      (= f '("--fen"))  :fen
      (= f '("--grid")) :grid
      :else             (throw (IllegalArgumentException. "Only --fen or --grid are accepted as flags")))))

(defn -main [& args]
  (print-board (board) (flag args)))
