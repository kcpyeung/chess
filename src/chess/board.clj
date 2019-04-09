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

(defn board [piece-maker]
  (let [bk (first (piece-maker "king" "black"))]
    (place-piece bk (make-ranks))))
