(ns chess.no-adjacent-kings-rules)

(defn- capture-kings [board]
  (->> (map (fn [[_ files]] files) board)
       (mapcat seq)
       (map second)
       (filter (fn [piece] (= :king (:name piece))))))

(defn- no-kings-in-adjacent-ranks-of-the-same-file [board]
  (let [[king1 king2] (capture-kings board)]
    (not
     (and
      (->> [king1 king2]
           (map :rank)
           (map (fn [rank-keyword] (Integer/parseInt (name rank-keyword))))
           (apply -)
           Math/abs
           (= 1))
      (->> [king1 king2]
           (map :file)
           (apply =))))))

(defn- no-kings-in-adjacent-files-of-the-same-rank [board]
  (let [adjacent-files [#{:a :b} #{:b :c} #{:c :d} #{:d :e} #{:e :f} #{:f :g} #{:g :h}]
        [king1 king2]  (capture-kings board)]
    (not
     (and
      (as-> [king1 king2] v
            (map :file v)
            (filter (fn [neighbours] (= neighbours (into #{} v))) adjacent-files)
            (not-every? empty? v))
      (->> [king1 king2]
           (map :rank)
           (map (fn [rank-keyword] (Integer/parseInt (name rank-keyword))))
           (apply =))))))

(defn no-adjacent-kings? [board]
  (and (no-kings-in-adjacent-files-of-the-same-rank board)
       (no-kings-in-adjacent-ranks-of-the-same-file board)))
