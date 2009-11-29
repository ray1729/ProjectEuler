(ns ray1729.projecteuler.euler087
  (:use [ray1729.clojure.math-extras :only (primes)]))

(def limit 50000000)

(defn pow [n m] (reduce * (repeat m n)))

(defn prime-powers [n] (take-while #(< % limit) (map #(pow % n) (primes))))

(defn euler087 [] (count (set (for [a (prime-powers 2)
                                    b (prime-powers 3)
                                    c (prime-powers 4)
                                    :let [s (+ a b c)]
                                    :while (< s limit)] s))))