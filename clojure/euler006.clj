(ns ray1729.projecteuler.euler006)

(defn square-of-sum
  ([n]
    (let [sum (/ (* n (inc n)) 2)] (* sum sum))))

(defn sum-of-squares
  ([n]
    (/ (* n (inc n) (inc (* 2 n))) 6)))

(defn euler006 ([] (- (square-of-sum 100) (sum-of-squares 100))))
