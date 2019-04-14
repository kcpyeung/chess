(ns chess.fen-printer-test
  (:require [clojure.test :refer :all]
            [chess.fen-printer :refer [board-to-string]]))

(deftest convert-a-rank-to-string
  (let [rank-4 {:4 {:a {:name :empty, :colour :empty, :sym :., :file :a, :rank :4}
                    :b {:name :empty, :colour :empty, :sym :., :file :b, :rank :4}
                    :c {:name :empty, :colour :empty, :sym :., :file :c, :rank :4}
                    :d {:name :knight, :colour :white, :sym :N, :rank :4, :file :d}
                    :e {:name :empty, :colour :empty, :sym :., :file :e, :rank :4}
                    :f {:name :rook, :colour :white, :sym :R, :rank :4, :file :f}
                    :g {:name :empty, :colour :empty, :sym :., :file :g, :rank :4}
                    :h {:name :king, :colour :black, :sym :k, :rank :4, :file :h}}}]
    (testing "print symbols of each piece in the given rank"
             (is (= "3N1R1k" (board-to-string rank-4))))))
