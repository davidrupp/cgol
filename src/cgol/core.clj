(ns cgol.core)

(def board
  [[0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 1 0 0 0 0]
   [0 0 0 0 0 1 0 0 0 0]
   [0 0 0 0 0 1 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]])

(def neighbor-deltas
  "[dx dy]"
  [[-1 -1] [0 -1] [1 -1]
   [-1  0]        [1  0]
   [-1  1] [0  1] [1  1]])

(defn value-at 
  [x y]
  (-> board
      (nth ,,, y)
      (nth ,,, x)))

(defn wrap 
  "Currently assumes a square board"
  [val]
  (let [c (count board)]
    (cond
     (< val 0) (+ val c)
     (>= val c) (- val c)
     :else val)))

(defn neighbors-of 
  [x y]
  (for [[dx dy] neighbor-deltas]
    (value-at (wrap (+ x dx)) (wrap (+ y dy)))))

(defn neighbor-counts []
  (for [y (range (count board))
        x (range (count (first board)))]
    (apply + (neighbors-of x y))))
