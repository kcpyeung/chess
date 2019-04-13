(ns chess.no-adjacent-kings-rules)

(defn- capture-kings [board]
  (->> (map (fn [[_ files]] files) board)
       (mapcat seq)
       (map second)
       (filter (fn [piece] (= :king (:name piece))))))

(defn- no-kings-in-adjacent-ranks-of-the-same-file [board]
  (let [[king1 king2] (capture-kings board)]
    (letfn
      [(in-adjacent-ranks []
                          (->> [king1 king2]
                               (map :rank)
                               (map (fn [rank-keyword] (Integer/parseInt (name rank-keyword))))
                               (apply -)
                               Math/abs
                               (= 1)))
       (in-same-file []
                     (->> [king1 king2]
                          (map :file)
                          (apply =)))]
      (not
       (and
        king1
        king2
        (in-adjacent-ranks)
        (in-same-file))))))

(defn- no-kings-in-adjacent-files-of-the-same-rank [board]
  (let [adjacent-files [#{:a :b} #{:b :c} #{:c :d} #{:d :e} #{:e :f} #{:f :g} #{:g :h}]
        [king1 king2]  (capture-kings board)]
    (letfn
      [(in-adjacent-files []
                          (as-> [king1 king2] v
                                (map :file v)
                                (filter (fn [neighbours] (= neighbours (into #{} v))) adjacent-files)
                                (not-every? empty? v)))
       (in-same-rank []
                     (->> [king1 king2]
                          (map :rank)
                          (map (fn [rank-keyword] (Integer/parseInt (name rank-keyword))))
                          (apply =)))]
      (not
       (and
        king1
        king2
        (in-adjacent-files)
        (in-same-rank))))))

(defn no-adjacent-kings? [board]
  (and (no-kings-in-adjacent-files-of-the-same-rank board)
       (no-kings-in-adjacent-ranks-of-the-same-file board)))
