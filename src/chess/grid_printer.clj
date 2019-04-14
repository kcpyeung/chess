(ns chess.grid-printer)

(defn- print-a-piece [[_ {piece-symbol :sym}]]
  (str "  " (name piece-symbol)))

(defn- print-a-rank [rank]
  (let [line (->> (map #(print-a-piece %) rank)
                  (apply str))]
    (str line "\n")))

(defn- board-to-ranks [board]
  (->> (seq board)
       (map second)
       seq))

(defn board-to-string [board]
  (->> (board-to-ranks board)
       (map #(print-a-rank %))
       (reduce str)))
