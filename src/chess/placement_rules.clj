(ns chess.placement-rules
  (:require [chess.no-adjacent-kings-rules :refer [no-adjacent-kings?]]))

(defn valid? [board]
  (no-adjacent-kings? board))
