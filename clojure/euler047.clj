(ns ray1729.projecteuler.euler047
  ;(:require )
  (:use [ray1729.clojure.math-extras :only (factorize)])
  ;(:import )
  )

(defn has-n-distinct-factors? [m n]
  (= n (count (set (factorize m)))))

(first 
  (filter 
    (fn [n] (every? #(has-n-distinct-factors? % 4) (take 4 (iterate inc n))))
        (iterate inc 1)))
