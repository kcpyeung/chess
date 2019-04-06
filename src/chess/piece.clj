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

(def Piece
  (fn [colour name file rank]
    (let [c (s/lower-case colour)
          n (s/lower-case name)]
      {:name   n,
       :colour c
       :file   (s/lower-case file)
       :rank   rank
       :sym    (as-> (symbols n) letter
                     (if (= c "white") (s/upper-case letter) letter))})))
