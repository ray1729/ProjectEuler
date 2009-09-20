(ns ray1729.projecteuler.euler007
 (:use [ray1729.projecteuler.euler003 :only (factorize)]))

(defn prime? ([n] (= 1 (count (factorize n)))))

(defn primes ([] (concat [2 3] (filter prime? (iterate #(+ 2 %) 5)))))

(defn euler007 ([] (last (take 10001 (primes)))))
