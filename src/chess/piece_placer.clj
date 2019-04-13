(ns chess.piece-placer
  (:use [chess.core :only [ascii-to-keyword]]))

(defn- next-empty [board random]
  (letfn
    [(random-rank-file []
                       [(keyword (str (inc (.nextInt random 8))))
                        (ascii-to-keyword (+ 97 (.nextInt random 8)))])]
    (loop [[rank file]   (random-rank-file)]
      (let [rank-on-board (board rank)
            piece         (rank-on-board file)]
        (if (= :. (:sym piece))
          [rank file]
          (recur (random-rank-file)))))))

(defn get-piece-placer [random]
  (fn [board piece]
    (let [[rank-of-piece file-of-piece]            (next-empty board random)
          rank-containing-piece                    (board rank-of-piece)]
      (->> (assoc piece :rank rank-of-piece :file file-of-piece)
           (hash-map file-of-piece)
           (merge rank-containing-piece)
           (assoc {} rank-of-piece)
           (merge board)))))
