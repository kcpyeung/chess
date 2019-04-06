(ns chess.board-test
  (:require [clojure.test :refer :all]
            [chess.board :refer :all]))

(deftest make-new-board
  (let [board (board)]
    (testing "has 8 ranks"
             (is (= 8 (count board))))

    (testing "every rank has 8 files"
             (is (every? #(= 8 %) (map #(count (nth % 1)) board))))))
