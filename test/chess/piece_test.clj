(ns chess.piece-test
  (:require [clojure.test :refer :all]
            [chess.piece :refer :all]))

(deftest make-new-pieces
  (let [piece (Piece "white" "pawn" "a" "1")]
    (testing "has a name"
             (is (= "pawn" (:name piece))))

    (testing "has a colour"
             (is (= "white" (:colour piece))))

    (testing "has a file"
             (is (= "a" (:file piece))))

    (testing "has a rank"
             (is (= "1" (:rank piece))))))

(deftest make-empty-piece
  (let [piece (EmptyPiece "b" "4")]
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
           (is (= "N" (:sym (Piece "white" "knight" "a" "1"))))
           (is (= "n" (:sym (Piece "black" "Knight" "a" "1"))))
           (is (= "." (:sym (EmptyPiece "a" "1"))))))
