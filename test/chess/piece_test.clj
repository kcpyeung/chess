(ns chess.piece-test
  (:require [clojure.test :refer :all]
            [chess.piece :refer :all]))

(deftest make-new-pieces
  (let [piece (make "white" "pawn" "a" "1")]
    (testing "has a name"
             (is (= "pawn" (:name piece))))

    (testing "has a colour"
             (is (= "white" (:colour piece))))

    (testing "has a file"
             (is (= "a" (:file piece))))

    (testing "has a rank"
             (is (= "1" (:rank piece))))))
