(ns chess.board)

(defn- a-h []
  (let [to-string (comp str char)]
    (map to-string (range 97 105))))

(defn- Rank [number]
  (->> (a-h)
       (map #(str % number))))

(defn Board []
  (reduce (fn [m k] (assoc m k (Rank k))) {} (range 1 9)))
