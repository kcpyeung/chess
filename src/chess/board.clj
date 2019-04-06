(ns chess.board)

(defn- a-h []
  (let [to-string (comp str char)]
    (map to-string (range 97 105))))

(defn- rank [number]
  (->> (a-h)
       (map #(str % number))))

(defn board []
  (reduce (fn [m k] (assoc m k (rank k))) {} (range 1 9)))
