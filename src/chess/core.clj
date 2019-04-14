(ns chess.core
  (:require [chess.board :refer [board]])
  (:require [chess.grid-printer])
  (:require [chess.fen-printer])
  (:gen-class))

(defn- flag [& args]
  (let [f (first args)]
    (cond
      (nil? f)          :fen
      (= f '("--fen"))  :fen
      (= f '("--grid")) :grid
      :else             (throw (IllegalArgumentException. "Only --fen or --grid are accepted as flags")))))

(defn -main [& args]
  (let [board (board)]
    (if (= :grid (flag args))
      (println (chess.grid-printer/board-to-string board))
      (println (chess.fen-printer/board-to-string board)))))
