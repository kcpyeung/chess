(ns chess.board
  (:use [chess.core :only [ascii-to-string]]))

(defn- a-h []
  (map ascii-to-string (range 97 105)))

(defn- cell [number]
  (->> (a-h)
       (map #(str % number))))

(def ranks (range 1 9))

(defn board []
  (reduce (fn [m k] (assoc m k (cell k))) {} ranks))
