(ns chess.board
  (:use [chess.core :only [ascii-to-string]]
        [chess.piece :only [empty-piece]]))

(defn- a-h []
  (map ascii-to-string (range 97 105)))

(defn- cell [rank]
  (->> (a-h)
       (map (fn [file] {(str file rank) (empty-piece file rank)}))))

(def ranks (range 1 9))

(defn board []
  (reduce (fn [m k] (assoc m k (cell k))) {} ranks))
