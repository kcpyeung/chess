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

(defn piece [colour name]
  (let [c (s/lower-case colour)
        n (s/lower-case name)]
    {:name   n,
     :colour c
     :sym    (as-> (symbols n) letter
                   (cond
                     (= c "white") (s/upper-case letter)
                     (= c "black") letter
                     :else         "."))}))

(defn empty-piece [file rank]
  (-> (piece "empty" "empty")
      (assoc :file file)
      (assoc :rank rank)))
