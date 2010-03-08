(ns ray1729.projecteuler.euler205
 (:use [clojure.contrib.seq-utils :only (frequencies)]))

(def outcomes4 (frequencies (for [a (range 1 5)
                                  b (range 1 5)
                                  c (range 1 5)
                                  d (range 1 5)
                                  e (range 1 5)
                                  f (range 1 5)
                                  g (range 1 5)
                                  h (range 1 5)
                                  i (range 1 5)]
                              (reduce + [a b c d e f g h i]))))

(def outcomes6 (frequencies (for [a (range 1 7)
                                  b (range 1 7)
                                  c (range 1 7)
                                  d (range 1 7)
                                  e (range 1 7)
                                  f (range 1 7)]
                              (reduce + [a b c d e f]))))

(def nwins (reduce + (for [s (keys outcomes4)
                           t (keys outcomes6)
                           :when (> s t)]
                       (* (get outcomes4 s) (get outcomes6 t)))))

(defn pow [m n] (reduce * (repeat n m)))

(defn euler205 [] (with-precision 7 (/ (BigDecimal. nwins) (* (pow 4 9) (pow 6 6)))))
