(ns chess.no-castling-rules)

(defn- black-king-and-left-rook [board]
  (let [rank-8 (:8 board)
        h8     (:sym (:h rank-8))
        e8     (:sym (:e rank-8))]
    (and (= h8 :r) (= e8 :k))))

(defn- black-king-and-right-rook [board]
  (let [rank-8 (:8 board)
        a8     (:sym (:a rank-8))
        e8     (:sym (:e rank-8))]
    (and (= a8 :r) (= e8 :k))))

(defn- white-king-and-left-rook [board]
  (let [rank-1 (:1 board)
        a1     (:sym (:a rank-1))
        e1     (:sym (:e rank-1))]
    (and (= a1 :R) (= e1 :K))))

(defn- white-king-and-right-rook [board]
  (let [rank-1 (:1 board)
        h1     (:sym (:h rank-1))
        e1     (:sym (:e rank-1))]
    (and (= h1 :R) (= e1 :K))))

(defn- castling [board]
  (or
   (black-king-and-left-rook board)
   (black-king-and-right-rook board)
   (white-king-and-left-rook board)
   (white-king-and-right-rook board)))

(defn no-castling? [board]
  (not (castling board)))
