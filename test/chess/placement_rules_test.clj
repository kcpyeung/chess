(ns chess.placement-rules-test
  (:require [clojure.test :refer :all]
            [chess.placement-rules :refer [valid?]]))

(deftest no-adjacent-kings
  (testing "adjacent files"
           (testing "invalid if kings are in adjacent files in the same rank"
                    (let [board        {:6 {:a {:name :king, :colour :white, :rank :6, :file :a, :sym :K}
                                            :b {:name :king, :colour :black, :rank :6, :file :b, :sym :k}}}]
                      (is (not (valid? board)))))

           (testing "valid if kings are separated by at least 1 file in the same rank"
                    (let [board        {:6 {:a {:name :king, :colour :white, :rank :6, :file :a, :sym :K}
                                            :c {:name :king, :colour :black, :rank :6, :file :c, :sym :k}}}]
                      (is (valid? board)))))

  (testing "adjacent ranks"
           (testing "invalid if kings are in adjacent ranks in the same file"
                    (let [board        {:6 {:a {:name :king, :colour :white, :rank :6, :file :a, :sym :K}}
                                        :7 {:a {:name :king, :colour :black, :rank :7, :file :a, :sym :k}}}]
                      (is (not (valid? board)))))

           (testing "valid if kings are separated by at least 1 rank in the same file"
                    (let [board        {:6 {:a {:name :king, :colour :white, :rank :6, :file :a, :sym :K}}
                                        :8 {:a {:name :king, :colour :black, :rank :8, :file :a, :sym :k}}}]
                      (is (valid? board)))))

  (testing "diagonal kings"
           (testing "are not valid"
                    (let [board        {:3 {:d {:name :king, :colour :white, :rank :3, :file :d, :sym :K}}
                                        :4 {:e {:name :king, :colour :black, :rank :4, :file :e, :sym :k}}}]
                      (is (not (valid? board)))))))

(deftest no-pawns-in-promotion-square
  (testing "8th rank"
           (testing "should not contain white pawns due to promotion"
                    (let [board        {:8 {:a {:name :pawn, :colour :white, :rank :8, :file :a, :sym :P}
                                            :b {:name :rook :colour :white, :rank :8, :file :b, :sym :R}}}]
                      (is (not (valid? board)))))

           (testing "should not contain black pawns due to normal piece rule"
                    (let [board        {:8 {:a {:name :pawn, :colour :black, :rank :8, :file :a, :sym :p}
                                            :b {:name :rook :colour :black, :rank :8, :file :b, :sym :r}}}]
                      (is (not (valid? board))))))

  (testing "1st rank"
           (testing "should not contain black pawns due to promotion"
                    (let [board        {:1 {:a {:name :pawn, :colour :black, :rank :1, :file :a, :sym :p}
                                            :b {:name :rook :colour :black, :rank :1, :file :b, :sym :r}}}]
                      (is (not (valid? board)))))

           (testing "should not contain white pawns due to normal piece rule"
                    (let [board        {:1 {:a {:name :pawn, :colour :white, :rank :1, :file :a, :sym :P}
                                            :b {:name :rook :colour :white, :rank :1, :file :b, :sym :R}}}]
                      (is (not (valid? board)))))))

(deftest no-castling-possibility
  (testing "8th rank"
           (testing "should not contain unmoved black king and left rook"
                    (let [board        {:8 {:a {:name :empty, :colour :empty, :rank :8, :file :a, :sym :.}
                                            :b {:name :empty, :colour :empty, :rank :8, :file :b, :sym :.}
                                            :c {:name :empty, :colour :empty, :rank :8, :file :c, :sym :.}
                                            :d {:name :empty, :colour :empty, :rank :8, :file :d, :sym :.}
                                            :e {:name :king, :colour :black, :rank :8, :file :e, :sym :k}
                                            :f {:name :empty, :colour :empty, :rank :8, :file :f, :sym :.}
                                            :g {:name :empty, :colour :empty, :rank :8, :file :g, :sym :.}
                                            :h {:name :rook, :colour :black, :rank :8, :file :h, :sym :r}}}]
                      (is (not (valid? board)))))

           (testing "should not contain unmoved black king and right rook"
                    (let [board        {:8 {:a {:name :rook, :colour :black, :rank :8, :file :a, :sym :r}
                                            :b {:name :empty, :colour :empty, :rank :8, :file :b, :sym :.}
                                            :c {:name :empty, :colour :empty, :rank :8, :file :c, :sym :.}
                                            :d {:name :empty, :colour :empty, :rank :8, :file :d, :sym :.}
                                            :e {:name :king, :colour :black, :rank :8, :file :e, :sym :k}
                                            :f {:name :empty, :colour :empty, :rank :8, :file :f, :sym :.}
                                            :g {:name :empty, :colour :empty, :rank :8, :file :g, :sym :.}
                                            :h {:name :empty, :colour :empty, :rank :8, :file :h, :sym :.}}}]
                      (is (not (valid? board))))))

  (testing "1st rank"
           (testing "should not contain unmoved white king and left rook"
                    (let [board        {:1 {:a {:name :rook, :colour :white, :rank :1, :file :a, :sym :R}
                                            :b {:name :empty, :colour :empty, :rank :1, :file :b, :sym :.}
                                            :c {:name :empty, :colour :empty, :rank :1, :file :c, :sym :.}
                                            :d {:name :empty, :colour :empty, :rank :1, :file :d, :sym :.}
                                            :e {:name :king, :colour :white, :rank :1, :file :e, :sym :K}
                                            :f {:name :empty, :colour :empty, :rank :1, :file :f, :sym :.}
                                            :g {:name :empty, :colour :empty, :rank :1, :file :g, :sym :.}
                                            :h {:name :empty, :colour :empty, :rank :1, :file :h, :sym :.}}}]
                      (is (not (valid? board)))))

           (testing "should not contain unmoved white king and right rook"
                    (let [board        {:1 {:a {:name :empty, :colour :empty, :rank :1, :file :a, :sym :.}
                                            :b {:name :empty, :colour :empty, :rank :1, :file :b, :sym :.}
                                            :c {:name :empty, :colour :empty, :rank :1, :file :c, :sym :.}
                                            :d {:name :empty, :colour :empty, :rank :1, :file :d, :sym :.}
                                            :e {:name :king, :colour :white, :rank :1, :file :e, :sym :K}
                                            :f {:name :empty, :colour :empty, :rank :1, :file :f, :sym :.}
                                            :g {:name :empty, :colour :empty, :rank :1, :file :g, :sym :.}
                                            :h {:name :rook, :colour :white, :rank :1, :file :h, :sym :R}}}]
                      (is (not (valid? board)))))))
