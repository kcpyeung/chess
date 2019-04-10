(ns chess.board
  (:use [chess.core :only [ascii-to-string]]
        [chess.piece :only [empty-piece]]))

(def files (map ascii-to-string (range 97 105)))

(def ranks (map str (range 8 0 -1)))

(defn- make-files [rank]
  (reduce (fn [m file] (assoc m file (empty-piece file rank))) {} files))

(defn- make-ranks []
  (reduce (fn [m rank] (assoc m rank (make-files rank))) {} ranks))

(defn- place-piece [piece board]
  (let [rank-of-piece            (:rank piece)
        file-of-piece            (:file piece)
        rank-containing-piece    (board rank-of-piece)]
    (->> {file-of-piece piece}
         (merge rank-containing-piece)
         (assoc {} rank-of-piece)
         (merge board))))

; TODO permutate all colours and pieces
(def colour-piece [["king" "black"] ["king" "white"]])

(defn board [piece-maker]
  (let [board  (make-ranks)
        pieces (mapcat (fn [[piece-name piece-colour]] (piece-maker piece-name piece-colour)) colour-piece)]
    (reduce (fn [current-board new-piece] (place-piece new-piece current-board)) board pieces)))
