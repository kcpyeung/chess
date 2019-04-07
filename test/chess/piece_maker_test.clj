(ns chess.piece-maker-test
  (:require [clojure.test :refer :all]
            [chess.piece-maker :refer :all]))

(deftest create-piece-maker-with-a-seed
  (let [piece-maker (get-piece-maker 3)]
    (testing "makes random numbers of pawns, including 0 and 8"
             (is (= 5 (count (piece-maker "pawn"))))
             (is (= 6 (count (piece-maker "pawn"))))
             (is (= 0 (count (piece-maker "pawn"))))
             (is (= 3 (count (piece-maker "pawn"))))
             (is (= 8 (count (piece-maker "pawn")))))

    (testing "0, 1, or 2 knights"
             (is (= 2 (count (piece-maker "knight"))))
             (is (= 2 (count (piece-maker "knight"))))
             (is (= 2 (count (piece-maker "knight"))))
             (is (= 2 (count (piece-maker "knight"))))
             (is (= 1 (count (piece-maker "knight"))))
             (is (= 2 (count (piece-maker "knight"))))
             (is (= 1 (count (piece-maker "knight"))))
             (is (= 0 (count (piece-maker "knight")))))

    (testing "queen or no queen"
             (is (= 0 (count (piece-maker "queen"))))
             (is (= 0 (count (piece-maker "queen"))))
             (is (= 1 (count (piece-maker "queen"))))
             (is (= 0 (count (piece-maker "queen"))))
             (is (= 0 (count (piece-maker "queen"))))
             (is (= 0 (count (piece-maker "queen"))))
             (is (= 1 (count (piece-maker "queen"))))
             (is (= 1 (count (piece-maker "queen"))))
             (is (= 0 (count (piece-maker "queen"))))
             (is (= 0 (count (piece-maker "queen"))))
             (is (= 0 (count (piece-maker "queen"))))
             (is (= 0 (count (piece-maker "queen"))))
             (is (= 0 (count (piece-maker "queen"))))
             (is (= 1 (count (piece-maker "queen")))))

    (testing "always a king"
             (is (= 1 (count (piece-maker "king"))))
             (is (= 1 (count (piece-maker "king"))))
             (is (= 1 (count (piece-maker "king"))))
             (is (= 1 (count (piece-maker "king"))))
             (is (= 1 (count (piece-maker "king"))))
             (is (= 1 (count (piece-maker "king"))))
             (is (= 1 (count (piece-maker "king"))))
             (is (= 1 (count (piece-maker "king"))))
             (is (= 1 (count (piece-maker "king"))))
             (is (= 1 (count (piece-maker "king"))))
             (is (= 1 (count (piece-maker "king"))))
             (is (= 1 (count (piece-maker "king"))))
             (is (= 1 (count (piece-maker "king"))))
             (is (= 1 (count (piece-maker "king")))))))

(deftest all-combinations-are-possible
  (let [piece-maker (get-piece-maker 3)
        pieces      (->> (range 5)
                         (map (fn [_] (piece-maker "pawn")))
                         flatten)]
    (testing "pieces are both black and white"
             (is
              (= #{"black" "white"}
                 (->> (map :colour pieces)
                      set))))

    (testing "pieces are across all ranks"
             (is
              (= #{"1" "2" "3" "4" "5" "6" "7" "8"}
                 (->> (map :rank pieces)
                      set))))

    (testing "pieces are across all files"
             (is
              (= #{"a" "b" "c" "d" "e" "f" "g" "h"}
                 (->> (map :file pieces)
                      set))))))
