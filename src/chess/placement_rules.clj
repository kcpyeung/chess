(ns chess.placement-rules)

(defn- no-kings-in-adjacent-ranks-of-the-same-file [board]
  (->>
   (reduce
    (fn [acc [_ files]]
      (map (fn [[_ piece]] (if (= :king (:name piece)) (conj acc piece))) files))
    [] board)
   flatten
   (map :rank)
   (map (fn [rank-keyword] (Integer/parseInt (name rank-keyword))))
   (apply -)
   Math/abs
   (= 1)
   not))

(defn- no-kings-in-adjacent-files [board]
  (let [adjacent-files [[:a :b] [:b :c] [:c :d] [:d :e] [:e :f] [:f :g] [:g :h]]]
    (->>
     (mapcat
      (fn [[_ files]]
        (map (fn [[left right]] [(left files) (right files)]) adjacent-files))
      board)
     (map (fn [[left right]] [(:name left) (:name right)]))
     (map (fn [[left right]] (= left right :king)))
     (not-any? true?))))

(defn valid? [board]
  (and (no-kings-in-adjacent-files board)
       (no-kings-in-adjacent-ranks-of-the-same-file board)))
