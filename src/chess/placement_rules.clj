(ns chess.placement-rules
  (:require [chess.core :refer [ascii-to-keyword]]))

(defn- no-adjacent-kings [board]
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
  (no-adjacent-kings board))
