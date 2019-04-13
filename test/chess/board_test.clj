(ns chess.board-test
  (:require [clojure.test :refer :all]
            [chess.board :refer [board]]
            [chess.piece-maker :refer [get-piece-maker]]
            [chess.piece-placer :refer [get-piece-placer]]))

(deftest make-new-board
  (let [random (java.util.Random. 1)
        board  (board (get-piece-placer random) (get-piece-maker random))]
    (testing "has 8 ranks"
             (is (= 8 (count board))))

    (testing "every rank has 8 files"
             (is (every? #(= 8 %) (map #(count (nth % 1)) board))))))

(deftest placing-pieces-on-board
  (let [random    (java.util.Random. 1)
        new-board (board (get-piece-placer random) (get-piece-maker random))
        a2        (:a (:2 new-board))
        d4        (:d (:4 new-board))]
    (testing "board places white king on at a2 itself using Piece Maker"
             (is (= a2 {:name :king, :colour :white, :file :a, :rank :2, :sym :K})))

    (testing "board places black king on at d4 itself using Piece Maker"
             (is (= d4 {:name :king, :colour :black, :file :d, :rank :4, :sym :k})))))
