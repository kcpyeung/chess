(ns chess.piece-maker
  (:use [chess.piece :only [piece]]
        [chess.core :only [ascii-to-string]]))

(def max-pieces
  {"pawn"   8
   "rook"   2
   "knight" 2
   "bishop" 2
   "queen"  1})

(defn get-piece-maker [seed]
  (let [random (java.util.Random. seed)]
    (fn [name colour]
      (let [file   (fn [] (ascii-to-string (+ 97 (.nextInt random 8))))
            rank   (fn [] (str (inc (.nextInt random 8))))]
        (cond
          (= name "king") (list (piece colour "king" (file) (rank)))
          :else           (->> name
                               max-pieces
                               inc
                               (.nextInt random)
                               range
                               (map (fn [_] (piece colour name (file) (rank))))))))))
