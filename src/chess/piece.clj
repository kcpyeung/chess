(ns chess.piece)

(defn make [colour name file rank]
  {:name   name,
   :colour colour
   :file   file
   :rank   rank})
