(ns ray1729.projecteuler.euler046
  ;(:require )
  (:use [ray1729.clojure.math-extras :only (prime?)])
  ;(:import )
  )

(def twice-squares (map #(* 2 % %) (iterate inc 1)))

(def odd-composites (filter #(not (prime? %)) (iterate #(+ % 2) 9)))

(defn sum-of-prime-and-twice-square? [n]
  (some prime? (map #(- n %) (take-while #(< % n) twice-squares))))

(defn euler046 []
    (first (filter #(not (sum-of-prime-and-twice-square? %)) odd-composites)))