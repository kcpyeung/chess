(ns chess.piece-test
  (:require [clojure.test :refer :all]
            [chess.piece :refer :all]))

(deftest make-new-pieces
  (let [piece (piece "white" "pawn")]
    (testing "has a name"
             (is (= "pawn" (:name piece))))

    (testing "has a colour"
             (is (= "white" (:colour piece))))))

(deftest make-empty-piece
  (let [piece (empty-piece "b" "4")]
    (testing "has a name"
             (is (= "empty" (:name piece))))

    (testing "has a colour"
             (is (= "empty" (:colour piece))))

    (testing "has a file"
             (is (= "b" (:file piece))))

    (testing "has a rank"
             (is (= "4" (:rank piece))))))

(deftest colour-aware-symbols
  (testing "white pieces are capitalised"
           (is (= "N" (:sym (piece "white" "knight"))))
           (is (= "n" (:sym (piece "black" "Knight"))))
           (is (= "." (:sym (empty-piece "a" "1"))))))
