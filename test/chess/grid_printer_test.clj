(ns chess.grid-printer-test
  (:require [clojure.test :refer :all]
            [chess.grid-printer :refer [board-to-string]]))

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
             (is (= "  .  .  .  N  .  R  .  k\n" (board-to-string rank-4))))))

(deftest convert-multiple-ranks-to-a-string
  (let [ranks {:4 {:a {:name :empty, :colour :empty, :sym :., :file :a, :rank :4}
                   :b {:name :empty, :colour :empty, :sym :., :file :b, :rank :4}
                   :c {:name :empty, :colour :empty, :sym :., :file :c, :rank :4}
                   :d {:name :knight, :colour :white, :sym :N, :rank :4, :file :d}
                   :e {:name :empty, :colour :empty, :sym :., :file :e, :rank :4}
                   :f {:name :rook, :colour :white, :sym :R, :rank :4, :file :f}
                   :g {:name :empty, :colour :empty, :sym :., :file :g, :rank :4}
                   :h {:name :king, :colour :black, :sym :k, :rank :4, :file :h}}
               :3 {:a {:name :queen, :colour :black, :sym :q, :file :a, :rank :3}
                   :b {:name :pawn, :colour :black, :sym :p, :file :b, :rank :3}
                   :c {:name :empty, :colour :empty, :sym :., :file :c, :rank :3}
                   :d {:name :knight, :colour :black, :sym :n, :rank :3, :file :d}
                   :e {:name :empty, :colour :empty, :sym :., :file :e, :rank :3}
                   :f {:name :rook, :colour :white, :sym :R, :rank :3, :file :f}
                   :g {:name :bishop, :colour :black, :sym :b, :file :g, :rank :3}
                   :h {:name :king, :colour :white, :sym :K, :rank :3, :file :h}}}]
    (testing "print each rank on a line"
             (is
              (= "  .  .  .  N  .  R  .  k\n  q  p  .  n  .  R  b  K\n"
                 (board-to-string ranks))))))
