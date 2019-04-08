(ns chess.board
  (:use [chess.core :only [ascii-to-string]]
        [chess.piece :only [empty-piece]]))

(def files (map ascii-to-string (range 97 105)))

(def ranks (range 1 9))

(defn- make-files [rank]
  (reduce (fn [m file] (assoc m file (empty-piece file rank))) {} files))

(defn- make-ranks []
  (reduce (fn [m rank] (assoc m rank (make-files rank))) {} ranks))

(def board make-ranks)
