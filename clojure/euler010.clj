(ns ray1729.projecteuler.euler010
  (:use [ray1729.projecteuler.euler007 :only (primes)]))

(defn euler010 ([] (reduce + (take-while #(< % 2000000) (primes)))))