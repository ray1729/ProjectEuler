(ns ray1729.projecteuler.euler058
  ;(:require )
  (:use ['ray1729.clojure.math-extras :only [prime?]])
  ;(:import )
  )

(defn numbers-on-diagonal [n]
  (if (= 1 n) [1] (take 4 (iterate #(inc (- % n)) (* n n)))))

(defn solve-recursively [n num-primes]
  (let [n (+ n 2)
        np (+ num-primes (count (filter prime? (numbers-on-diagonal n))))
        r (/ np (dec (* 2.0 n)))]
    ;(println (format "%d => %.3f" n r))
    (if (< r 0.1) n (recur n np))))

(defn euler058 [] (solve-recursively 1 0))
