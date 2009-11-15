(ns ray1729.projecteuler.euler072
  (:use [ray1729.clojure.math-extras :only (euler-totient)]))

(defn euler072 [] (reduce + (map euler-totient (range 2 1000001))))