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
           (testing "are valid"
                    (let [board        {:3 {:d {:name :king, :colour :white, :rank :3, :file :d, :sym :K}}
                                        :4 {:e {:name :king, :colour :black, :rank :4, :file :e, :sym :k}}}]
                      (is (valid? board))))))
