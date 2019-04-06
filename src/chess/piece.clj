(ns chess.piece)

(require
 '(clojure [string :as s]))

(def symbols
  {"rook"   "r"
   "knight" "n"
   "bishop" "b"
   "queen"  "q"
   "king"   "k"
   "pawn"   "p"})

(defn piece [colour name file rank]
  (let [c (s/lower-case colour)
        n (s/lower-case name)]
    {:name   n,
     :colour c
     :file   (s/lower-case file)
     :rank   rank
     :sym    (as-> (symbols n) letter
                   (cond
                     (= c "white") (s/upper-case letter)
                     (= c "black") letter
                     :else         "."))}))

(defn empty-piece [file rank]
  (piece "empty" "empty" file rank))
