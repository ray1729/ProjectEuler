(ns ray1729.projecteuler.euler023)

(defn proper-divisors
  ([n]
    (filter #(zero? (rem n %)) (range 1 (inc (int (/ n 2)))))))

(defn sum-proper-divisors
  ([n]
    (reduce + (proper-divisors n))))

(defn abundant? [n] (> (sum-proper-divisors n) n))

(def abundant? (memoize abundant?))

;(defn sums-of-pairs [coll]
;  (if (empty? coll) '()
;    (lazy-seq (concat (map #(+ (first coll) %) coll) (sums-of-pairs (rest coll))))))

(defn sum-abundant-pair? [n]
  (and (>= n 24) (some #(and (abundant? %) (abundant? (- n %))) (range 12 n))))

(reduce + (filter #(not (sum-abundant-pair? %)) (range 1 28123)))