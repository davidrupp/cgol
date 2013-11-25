(ns cgol.core)

(def init-board
  (list
   (list 0 0 0 0 0 0 0 0 0 0)
   (list 0 0 0 0 0 0 0 0 0 0)
   (list 0 0 0 0 0 0 0 0 0 0)
   (list 0 0 0 0 0 1 0 0 0 0)
   (list 0 0 0 0 0 1 0 0 0 0)
   (list 0 0 0 0 0 1 0 0 0 0)
   (list 0 0 0 0 0 0 0 0 0 0)
   (list 0 0 0 0 0 0 0 0 0 0)
   (list 0 0 0 0 0 0 0 0 0 0)
   (list 0 0 0 0 0 0 0 0 0 0)))

(def neighbor-deltas
  "[dx dy]"
  [[-1 -1] [0 -1] [1 -1]
   [-1  0]        [1  0]
   [-1  1] [0  1] [1  1]])

(defn value-at 
  [board x y]
  (-> board
      (nth ,,, y)
      (nth ,,, x)))

(defn wrap 
  "Currently assumes a square board"
  [val]
  (let [c (count init-board)]
    (cond
     (< val 0) (+ val c)
     (>= val c) (- val c)
     :else val)))

(defn neighbors-of 
  [board x y]
  (for [[dx dy] neighbor-deltas]
    (value-at board (wrap (+ x dx)) (wrap (+ y dy)))))

(defn evolve [board]
  (for [y (range (count board))
        x (range (count (first board)))]
    (let [n (apply + (neighbors-of board x y))
          v (value-at board x y)]
      (cond
       (= n 2) v
       (= n 3) 1
       :else 0))))
