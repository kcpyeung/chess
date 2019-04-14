(ns chess.no-adjacent-kings-rules)

(defn- capture-kings [board]
  (->> (map (fn [[_ files]] files) board)
       (mapcat seq)
       (map second)
       (filter (fn [piece] (= :king (:name piece))))))

(defn- kings-in-adjacent-ranks [king1 king2]
  (->> [king1 king2]
       (map :rank)
       (map (fn [rank-keyword] (Integer/parseInt (name rank-keyword))))
       (apply -)
       Math/abs
       (= 1)))

(defn- kings-in-adjacent-files [king1 king2]
  (let [adjacent-files [#{:a :b} #{:b :c} #{:c :d} #{:d :e} #{:e :f} #{:f :g} #{:g :h}]]
    (as-> [king1 king2] v
          (map :file v)
          (filter (fn [neighbours] (= neighbours (into #{} v))) adjacent-files)
          (not-every? empty? v))))

(defn- kings-in-same-rank [king1 king2]
  (->> [king1 king2]
       (map :rank)
       (map (fn [rank-keyword] (Integer/parseInt (name rank-keyword))))
       (apply =)))

(defn- kings-in-same-file [king1 king2]
  (->> [king1 king2]
       (map :file)
       (apply =)))

(defn- no-kings-in-adjacent-ranks-of-the-same-file [board]
  (let [[king1 king2]     (capture-kings board)]
    (not
     (and
      king1
      king2
      (kings-in-adjacent-ranks king1 king2)
      (kings-in-same-file king1 king2)))))

(defn- no-kings-in-adjacent-files-of-the-same-rank [board]
  (let [[king1 king2]     (capture-kings board)]
    (not
     (and
      king1
      king2
      (kings-in-adjacent-files king1 king2)
      (kings-in-same-rank king1 king2)))))

(defn- no-kings-in-diagonal-positions [board]
  (let [[king1 king2]     (capture-kings board)]
    (not
     (and
      king1
      king2
      (kings-in-adjacent-files king1 king2)
      (kings-in-adjacent-ranks king1 king2)))))

(defn no-adjacent-kings? [board]
  (and (no-kings-in-adjacent-files-of-the-same-rank board)
       (no-kings-in-adjacent-ranks-of-the-same-file board)
       (no-kings-in-diagonal-positions board)))
