(ns chess.board-test
  (:require [clojure.test :refer :all]
            [chess.board :refer [board]]
            [chess.piece-maker :refer [get-piece-maker]]))

(deftest make-new-board
  (let [board (board (get-piece-maker 1))]
    (testing "has 8 ranks"
             (is (= 8 (count board))))

    (testing "every rank has 8 files"
             (is (every? #(= 8 %) (map #(count (nth % 1)) board))))))

(deftest placing-pieces-on-board
  (let [new-board (board (get-piece-maker 1))
        f1        ((new-board "1") "f")]
    (testing "board places black king on at f1 itself using Piece Maker"
             (is (= f1 {:name "king", :colour "black", :file "f", :rank "1", :sym "k"})))))
