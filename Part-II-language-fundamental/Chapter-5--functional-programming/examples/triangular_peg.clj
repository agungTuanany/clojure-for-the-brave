:45(defn tri*
  "Generate lazy sequence of triangular numbers"
  ([] (tri* 0 1))
  ([sum n]
   (let [new-sum (+ sum n)]
     (cons new-sum (lazy-seq (tri* new-sum (inc n)))))))

(def tri (tri*))

(defn triangular?
  "Is the number triangular? e.g 1, 3, 6, 10, 15 etc"
  [n]
  (= n (last (take-while #(>= n %) tri))))

(triangular? 5)
;; => false
(triangular? 6)
;; => true

(defn row-tri
  "The triangular number at the end of row n"
  [n]
  (last (take n tri)))

(row-tri 1)
;; => 1
(row-tri 2)
;; => 3
(row-tri 3)
;; => 6
(row-tri 5)
;; => 15

(defn row-num
  "Return row number the position belongs to: pos 1 in row 1,
         position 2 and 3 in row 2, etc"
  [pos]
  (inc (count (take-while #(> pos %) tri))))

(row-num 1)
;; => 1
(row-num 2)
;; => 2
(row-num 5)
;; => 3
;; (row-num '(9 8))

(defn connect
  "Form a mutual connection between two positions"
  [board max-pos pos neighbor destination]
  (if (<= destination max-pos)
    (reduce (fn [new-board [p1 p2]]
              (assoc-in new-board [p1 :connections p2] neighbor))
            board
            [[pos destination] [destination pos]])
    board))

(connect {} 15 1 2 4)

(defn connect-right
  [board max-pos pos]
  (let [neighbor (inc pos)
        destination (inc neighbor)]
    (if-not (or (triangular? neighbor) (triangular? pos))
      (connect board max-pos pos neighbor destination)
      board)))

(defn connect-down-left
  [board max-pos pos]
  (let [row (row-num pos)
        neighbor (+ row pos)
        destination (+ 1 row neighbor)]
    (connect board max-pos pos neighbor destination)))

(defn connect-down-right
  [board max-pos pos]
  (let [row (row-num pos)
        neighbor (+ 1 row pos)
        destination (+ 2 row neighbor)]
    (connect board max-pos pos neighbor destination)))

(connect-down-left {} 15 1)
;; =>{1 {:connections {4 2}},
;;    4 {:connections {1 2}}}

(connect-down-right {} 15 3)
;; => {3 {:connections {10 6}},
;;     10 {:connections {3 6}}}

(defn add-pos
  "Pegs the position and perform connections"
  [board max-pos pos]
  (let [pegged-board (assoc-in board [pos :pegged] true)]
    (reduce (fn [new-board connection-creation-fn]
              (connection-creation-fn new-board max-pos pos))
            pegged-board
            [connect-right connect-down-left connect-down-right])))

(add-pos {} 15 1)
;; => {1 {:pegged true, :connections {4 2, 6 3}},
;;     4 {:connections {1 2}},
;;     6 {:connections {1 3}}}


(defn new-board
  "Creates a new board with the given number of rows"
  [rows]
  (let [initial-board {:rows rows}
        max-pos (row-tri rows)]
    (reduce (fn [board pos] (add-pos board max-pos pos))
              initial-board
              (range 1 (inc max-pos)))))

(defn pegged?
  "Does the position have a peg in it?"
  [board pos]
  (get-in board [pos :pegged]))

(def my-board (assoc-in (new-board 5) [4 :pegged] false))

(defn valid-moves
  "Return a map of all valid moves for pos, where the key is the
  destination and the value is the jumped position"
  [board pos]
  (into {}
        (filter (fn [[destination jumped]]
                  (and (not (pegged? board destination))
                       (pegged? board jumped)))
                (get-in board [pos :connections]))))

(valid-moves my-board 1)  ;; => {4 2}
(valid-moves my-board 6)  ;; => {4 5}
(valid-moves my-board 11) ;; => {4 2}
(valid-moves my-board 5)  ;; => {}
(valid-moves my-board 8)  ;; => }{

(defn valid-move?
  "Return jumped position if the move from p1 to p2 is valid,
     nil otherwise"
  [board p1 p2]
  (get (valid-moves board p1) p2))

(valid-move? my-board 8 4) ;; => nil
(valid-move? my-board 1 4) ;; => 2
