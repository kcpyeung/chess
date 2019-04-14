(ns chess.no-pawn-in-1st-or-8th-ranks-rules)

(defn- no-pawn-in [rank board]
  (->> (rank board)
       (map (fn [[_ files]] files))
       (map :sym)
       (not-any? (fn [sym] (or (= :P sym) (= :p sym))))))

(defn no-pawn-in-1st-or-8th-ranks? [board]
  (and
   (no-pawn-in :8 board)
   (no-pawn-in :1 board)))
