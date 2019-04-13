(ns chess.piece)

(require
 '(clojure [string :as s]))

(def symbols
  {:rook   :r
   :knight :n
   :bishop :b
   :queen  :q
   :king   :k
   :pawn   :p})

(defn piece [piece-colour piece-name]
  {:name   piece-name,
   :colour piece-colour
   :sym    (as-> (symbols piece-name) letter
                 (cond
                   (= piece-colour :white)  (keyword (s/upper-case (name letter)))
                   (= piece-colour :black)  letter
                   :else                    :.))})

(defn empty-piece [file rank]
  (-> (piece :empty :empty)
      (assoc :file file)
      (assoc :rank rank)))
