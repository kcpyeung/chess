(ns chess.piece-placer-test
  (:require [clojure.test :refer :all]
            [chess.piece-placer :refer [get-piece-placer]]))

(deftest placing-piece-on-empty-spot
  (let [board        {:6 {:a {:name :empty, :colour :empty, :rank :6, :file :a, :sym :.}}}
        piece-placer (get-piece-placer (java.util.Random. 1))
        new-board    (piece-placer board {:name :king, :colour :white, :sym :K})]
    (testing "places a piece on an empty spot"
             (is
              (= {:name :king, :colour :white, :rank :6, :file :a, :sym :K} (:a (:6 new-board)))))))

(deftest find-next-empty-spot-if-first-is-occupied-leaving-first-untouched
  (let [board        {:6 {:a {:name :king, :colour :white, :rank :6, :file :a, :sym :K}}
                      :4 {:d {:name :empty, :colour :empty, :rank :4, :file :d, :sym :.}}}
        piece-placer (get-piece-placer (java.util.Random. 1))
        new-board    (piece-placer board {:name :king, :colour :black, :sym :k})]
    (testing "leaves occupied spot untouched"
             (is
              (= {:name :king, :colour :white, :rank :6, :file :a, :sym :K} (:a (:6 new-board)))))

    (testing "places a piece on next empty spot"
             (is
              (= {:name :king, :colour :black, :rank :4, :file :d, :sym :k} (:d (:4 new-board)))))))
