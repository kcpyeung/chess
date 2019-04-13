(ns chess.board
  (:use [chess.core :only [ascii-to-keyword]]
        [chess.piece :only [empty-piece]]
        [chess.piece-maker :only [get-piece-maker]]
        [chess.piece-placer :only [get-piece-placer]]
        [clojure.math.combinatorics :only [cartesian-product]]))

(def files (map ascii-to-keyword (range 97 105)))

(def ranks (map (comp keyword str) (range 8 0 -1)))

(defn- make-files [rank]
  (reduce (fn [m file] (assoc m file (empty-piece file rank))) {} files))

(defn- make-ranks []
  (reduce (fn [m rank] (assoc m rank (make-files rank))) {} ranks))

(def colour-piece
  (cartesian-product [:king :queen :rook :knight :bishop :pawn] [:black :white]))

(defn board
  ([]
   (let [random (java.util.Random.)]
     (->> (get-piece-maker random)
          (board (get-piece-placer random)))))
  ([piece-placer piece-maker]
   (let [board       (make-ranks)
         pieces      (mapcat (fn [[piece-name piece-colour]] (piece-maker piece-name piece-colour)) colour-piece)]
     (reduce (fn [current-board new-piece] (piece-placer current-board new-piece)) board pieces))))
