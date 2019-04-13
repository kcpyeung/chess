(ns chess.placement-rules
  (:require [chess.no-adjacent-kings-rules :refer [no-adjacent-kings?]]
            [chess.no-pawn-in-1st-or-8th-ranks-rules :refer [no-pawn-in-1st-or-8th-ranks]]))

(defn valid? [board]
  (and
   (no-adjacent-kings? board)
   (no-pawn-in-1st-or-8th-ranks board)))
