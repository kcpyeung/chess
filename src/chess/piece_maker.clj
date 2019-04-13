(ns chess.piece-maker
  (:use [chess.piece :only [piece]]))

(def max-pieces
  {:pawn   8
   :rook   2
   :knight 2
   :bishop 2
   :queen  1})

(defn get-piece-maker [random]
  (fn [name colour]
    (cond
      (= name :king)  (list (piece colour :king))
      :else           (->> name
                           max-pieces
                           inc
                           (.nextInt random)
                           range
                           (map (fn [_] (piece colour name)))))))
