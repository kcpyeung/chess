(ns chess.printer
  (:require [chess.fen-printer])
  (:require [chess.grid-printer]))

(defmulti print-board
  (fn [_ format] format))

(defmethod print-board :fen
  [board _]
  (println (chess.fen-printer/board-to-string board)))

(defmethod print-board :grid
  [board _]
  (println (chess.grid-printer/board-to-string board)))
