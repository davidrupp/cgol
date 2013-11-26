(ns cgol.core)

(def blinker-board
  (list
   (list 0 0 0 0 0)
   (list 0 0 1 0 0)
   (list 0 0 1 0 0)
   (list 0 0 1 0 0)
   (list 0 0 0 0 0)))

(def glider-board
  (list
   (list 0 0 0 0 0 0)
   (list 0 0 1 0 0 0)
   (list 0 0 0 1 0 0)
   (list 0 1 1 1 0 0)
   (list 0 0 0 0 0 0)
   (list 0 0 0 0 0 0)))

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
  [board val]
  (let [c (count board)]
    (cond
     (< val 0) (+ val c)
     (>= val c) (- val c)
     :else val)))

(defn neighbors-of 
  [board x y]
  (for [[dx dy] neighbor-deltas]
    (value-at board (wrap board (+ x dx)) (wrap board (+ y dy)))))

(defn evolve [board]
  (for [y (range (count board))
        x (range (count (first board)))]
    (let [n (apply + (neighbors-of board x y))
          v (value-at board x y)]
      (cond
       (= n 2) v
       (= n 3) 1
       :else 0))))

(def gol
  (partial iterate #(partition (count %) (evolve %))))
