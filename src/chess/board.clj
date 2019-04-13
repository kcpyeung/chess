(ns chess.board
  (:use [chess.core :only [ascii-to-keyword]]
        [chess.piece :only [empty-piece]]
        [chess.piece-maker :only [get-piece-maker]]
        [clojure.math.combinatorics :only [cartesian-product]]))

(def files (map ascii-to-keyword (range 97 105)))

(def ranks (map (comp keyword str) (range 8 0 -1)))

(defn- make-files [rank]
  (reduce (fn [m file] (assoc m file (empty-piece file rank))) {} files))

(defn- make-ranks []
  (reduce (fn [m rank] (assoc m rank (make-files rank))) {} ranks))

(defn- rand-rank-file [random]
  [(keyword (str (inc (.nextInt random 8))))
   (ascii-to-keyword (+ 97 (.nextInt random 8)))])

(defn- next-empty [board random]
  (loop [[rank file]   (rand-rank-file random)]
    (let [rank-on-board (board rank)
          piece         (rank-on-board file)]
      (if (= :. (:sym piece))
        [rank file]
        (recur (rand-rank-file random))))))

(defn- get-piece-placer [random]
  (fn [board piece]
    (let [[rank-of-piece file-of-piece]            (next-empty board random)
          rank-containing-piece                    (board rank-of-piece)]
      (->> (assoc piece :rank rank-of-piece :file file-of-piece)
           (hash-map file-of-piece)
           (merge rank-containing-piece)
           (assoc {} rank-of-piece)
           (merge board)))))

(def colour-piece
  (cartesian-product [:king :queen :rook :knight :bishop :pawn] [:black :white]))

(defn board
  ([]
   (let [seed (System/currentTimeMillis)]
     (->> (get-piece-maker seed)
          (board (java.util.Random. seed)))))
  ([random piece-maker]
   (let [board       (make-ranks)
         pieces      (mapcat (fn [[piece-name piece-colour]] (piece-maker piece-name piece-colour)) colour-piece)
         place-piece (get-piece-placer random)]
     (reduce (fn [current-board new-piece] (place-piece current-board new-piece)) board pieces))))
