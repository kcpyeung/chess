(ns chess.board
  (:use [chess.core :only [ascii-to-string]]))

(defn- a-h []
  (map ascii-to-string (range 97 105)))

(defn- rank [number]
  (->> (a-h)
       (map #(str % number))))

(defn board []
  (reduce (fn [m k] (assoc m k (rank k))) {} (range 1 9)))
