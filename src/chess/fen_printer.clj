(ns chess.fen-printer)

(defn- print-a-piece [[_ {piece-symbol :sym}]]
  (name piece-symbol))

(defn- print-a-rank [rank]
  (let [symbols           (->> (map #(print-a-piece %) rank)
                               (apply str))
        symbol-partitions (partition-by identity symbols)
        freq              (map frequencies symbol-partitions)
        symbol-counts     (map first (map #(apply vector %) freq))]
    (->> (map (fn [[symbol count]] (if (= \. symbol) count symbol)) symbol-counts)
         (apply str))))

(defn- board-to-ranks [board]
  (->> (seq board)
       (map second)
       seq))

(defn board-to-string [board]
  (let [fen (->> (board-to-ranks board)
                 (map #(print-a-rank %))
                 (interpose "/")
                 (reduce str))]
    (str fen " w - - 0 1")))
