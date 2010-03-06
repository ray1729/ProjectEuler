(ns ray1729.projecteuler.euler077
  (:use [ray1729.clojure.math-extras :only (primes)]))

(defn prime-partitions [n]
  (letfn [(prime-partitions [n ps]
            (let [p (first ps)]
              (cond
                (empty? ps) 0
                (< n p) 0
                (= n p) 1
                :else         
                (+ (prime-partitions (- n p) ps) (prime-partitions n (rest ps))))))]
    (prime-partitions n (take-while #(<= % n) (primes)))))

(defn euler077 [target]
  (loop [n 1]
    (if (>= (prime-partitions n) target)
      n
      (recur (inc n)))))
