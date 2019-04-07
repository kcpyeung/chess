(ns chess.board
  (:use [chess.core :only [ascii-to-string]]
        [chess.piece :only [empty-piece]]))

(def files (map ascii-to-string (range 97 105)))

(def ranks (range 1 9))

(defn- cells [rank]
  (let [ranks            (take 8 (repeat rank))
        files-ranks      (map vector files ranks)
        file             (fn [file-rank] (nth file-rank 0))
        file-rank-str    (fn [file-rank] (str (file file-rank) rank))]
    (reduce
     (fn [m file-rank]
       (assoc m (file-rank-str file-rank) (empty-piece (file file-rank) rank)))
     {}
     files-ranks)))

(defn board []
  (reduce (fn [m rank] (assoc m rank (cells rank))) {} ranks))
