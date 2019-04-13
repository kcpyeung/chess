(ns chess.grid-printer)

(defn- piece-to-string [[_ {piece-symbol :sym}]]
  (str "  " (name piece-symbol)))

(defn rank-to-string [rank]
  (let [pieces (second (first (seq rank)))]
    (->> (map #(piece-to-string %) pieces)
         (reduce str))))
