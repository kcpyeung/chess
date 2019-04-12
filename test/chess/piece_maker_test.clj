(ns chess.piece-maker-test
  (:require [clojure.test :refer :all]
            [chess.piece-maker :refer :all]))

(deftest create-piece-maker-with-a-seed
  (let [piece-maker (get-piece-maker 3)]
    (testing "makes random numbers of pawns, including 0 and 8"
             (let [pieces      (->> (range 100)
                                    (map (fn [_] (piece-maker "pawn" "white"))))]
               (is
                (= #{0 1 2 3 4 5 6 7 8}
                   (set (map count pieces))))))

    (testing "0, 1, or 2 knights"
             (let [pieces      (->> (range 100)
                                    (map (fn [_] (piece-maker "knight" "black"))))]
               (is
                (= #{0 1 2}
                   (set (map count pieces))))))

    (testing "queen or no queen"
             (let [pieces      (->> (range 100)
                                    (map (fn [_] (piece-maker "queen" "white"))))]
               (is
                (= #{0 1}
                   (set (map count pieces))))))

    (testing "always a king"
             (let [pieces      (->> (range 100)
                                    (map (fn [_] (piece-maker "king" "black"))))]
               (is
                (= #{1}
                   (set (map count pieces))))))))

(deftest all-combinations-are-possible
  (let [piece-maker (get-piece-maker 3)
        pieces      (->> (range 5)
                         (map (fn [_] (piece-maker "pawn" "white")))
                         flatten)]
    (testing "is of the given colour"
             (is
              (= #{"white"}
                 (set (map :colour pieces)))))))
