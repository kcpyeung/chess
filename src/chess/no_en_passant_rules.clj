(ns chess.no-en-passant-rules)

(def en-passant-files
  {:a [:b]
   :b [:a :c]
   :c [:b :d]
   :d [:c :e]
   :e [:d :f]
   :f [:e :g]
   :g [:f :h]
   :h [:g]})

(defn- black-pawn? [rank-7 white-pawn-file-in-rank-5]
  (let [pos-to-check    (white-pawn-file-in-rank-5 en-passant-files)
        pieces-to-check (filter (fn [piece] (some #{(:file piece)} pos-to-check)) rank-7)]
    (filter (fn [piece] (= :p (:sym piece))) pieces-to-check)))

(defn- white-pawn? [rank-2 black-pawn-file-in-rank-2]
  (let [pos-to-check    (black-pawn-file-in-rank-2 en-passant-files)
        pieces-to-check (filter (fn [piece] (some #{(:file piece)} pos-to-check)) rank-2)]
    (filter (fn [piece] (= :P (:sym piece))) pieces-to-check)))

(defn- white-en-passant [board]
  (let [rank-5           (map second (seq (:5 board)))
        rank-7           (map second (seq (:7 board)))
        white-pawns      (filter (fn [piece] (if (= :P (:sym piece)) piece)) rank-5)
        white-pawn-files (map :file white-pawns)
        found?           (flatten (map #(black-pawn? rank-7 %) white-pawn-files))]
    (and
     (not (empty? found?))
     (not-any? true? found?))))

(defn- black-en-passant [board]
  (let [rank-4           (map second (seq (:4 board)))
        rank-2           (map second (seq (:2 board)))
        black-pawns      (filter (fn [piece] (if (= :p (:sym piece)) piece)) rank-4)
        black-pawn-files (map :file black-pawns)
        found?           (flatten (map #(white-pawn? rank-2 %) black-pawn-files))]
    (and
     (not (empty? found?))
     (not-any? true? found?))))

(defn- en-passant [board]
  (or
   (white-en-passant board)
   (black-en-passant board)))

(defn no-en-passant? [board]
  (not (en-passant board)))
