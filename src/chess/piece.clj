(ns chess.piece)

(def Piece
  (fn [colour name file rank]
    {:name   name,
     :colour colour
     :file   file
     :rank   rank}))
