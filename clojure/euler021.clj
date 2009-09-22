(ns ray1729.projecteuler.euler021)

; this is inefficient - only need to go as far as sqrt n if you save n/d for each
; divisor d you find (but don't forget special case for n a perfect square)
(defn proper-divisors
  ([n]
    (filter #(zero? (rem n %)) (range 1 (inc (int (/ n 2)))))))

(defn sum-proper-divisors
  ([n]
    (reduce + (proper-divisors n))))

; XXX this is inefficient, even with the memoized function
;(def sum-proper-divisors (memoize sum-proper-divisors))

;(defn amicable?
;  ([a b]
;    (and (= (sum-proper-divisors a) b) (= (sum-proper-divisors b) a))))

;(defn euler021
;  ([]
;    (reduce + (apply concat (for [ a (range 1 10000) b (range (inc a) 10000) :when (amicable? a b)] (list a b))))))

; this approach is better
(defn amicable-pair
  ([a]
    (let [b (sum-proper-divisors a)]
      (if (and (> b a) (= (sum-proper-divisors b) a))
        (list a b)
        '()))))

(defn euler021
  ([]
    (reduce + (apply concat (map amicable-pair (range 1 10000))))))
