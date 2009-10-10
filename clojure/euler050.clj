(ns ray1729.projecteuler.euler050
  ;(:require )
  (:use [ray1729.clojure.math-extras :only (primes prime?)])
  ;(:import )
  )

(defn find-prime-sums [coll]
    (filter #(prime? (reduce + %))
      (take-while #(< (reduce + %) 1000000) (map #(take % coll) (range 2 (count coll))))))

(defn find-all-prime-sums [coll]
  (if (< (count coll) 2)
    '()
    (cons (last (find-prime-sums coll)) (lazy-seq (find-all-prime-sums (rest coll))))))

(defn find-max-count [accum coll]
  (cond
    (empty? coll) accum
    (nil? (first coll)) (recur accum (rest coll))
    (> (count (first coll)) (count accum)) (recur (first coll) (rest coll))
    :else (recur accum (rest coll))))

; Taking n = 10000 below gives the prime 997651, which is the sum of the 543
; consecutive primes from 7..3943. Any sequence of 543 primes containing a prime
; greater than 3943 sums to at least 1001587, so in fact we could stop our
; search at 3943. This post-hoc justification a little uncomfortable.

(defn euler050 [n]
    (reduce + (find-max-count '() (find-all-prime-sums (take-while #(< % n) (primes))))))

