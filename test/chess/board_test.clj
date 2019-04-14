(ns chess.board-test
  (:require [clojure.test :refer :all]
            [chess.board :refer [board]]
            [chess.piece-maker :refer [get-piece-maker]]
            [chess.piece-placer :refer [get-piece-placer]]
            [chess.placement-rules :refer [valid?]]))

;(deftest make-new-board
;  (let [random (java.util.Random. 1)
;        board  (board (get-piece-placer random) (get-piece-maker random))]
;    (testing "has 8 ranks"
;             (is (= 8 (count board))))
;
;    (testing "every rank has 8 files"
;             (is (every? #(= 8 %) (map #(count (nth % 1)) board))))
;
;    (testing "repeats until board is valid"
;             (is (valid? board)))))

;(deftest placing-pieces-on-board
;  (let [random    (java.util.Random. 1)
;        new-board (board (get-piece-placer random) (get-piece-maker random))
;        c2        (:c (:2 new-board))
;        h8        (:h (:8 new-board))]
;    (testing "board places white king on at c2 itself using Piece Maker"
;             (is (= c2 {:name :king, :colour :white, :file :c, :rank :2, :sym :K})))
;
;    (testing "board places black king on at h8 itself using Piece Maker"
;             (is (= h8 {:name :king, :colour :black, :file :h, :rank :8, :sym :k})))))
