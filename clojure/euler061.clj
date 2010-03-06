(ns ray1729.projecteuler.euler061
  (:use [clojure.contrib.combinatorics :only (permutations)]))

(def *lower-bound* 1000)
(def *upper-bound* 10000)

(defn P3 [n] (/ (* n (+ n 1)) 2))
(defn P4 [n] (* n n))
(defn P5 [n] (/ (* n (- (* 3 n) 1)) 2))
(defn P6 [n] (* n (- (* 2 n) 1)))
(defn P7 [n] (/ (* n (- (* 5 n) 3)) 2))
(defn P8 [n] (* n (- (* 3 n) 2)))

(defn figurates [f] (set
                      (drop-while #(< % *lower-bound*)
                        (take-while #(< % *upper-bound*)
                          (map f (iterate inc 1))))))

(def triangles   (figurates P3))
(def squares     (figurates P4))
(def pentagonals (figurates P5))
(def hexagonals  (figurates P6))
(def heptagonals (figurates P7))
(def octagonals  (figurates P8))

(defn munge
  "Given 2 4-digit integers, construct a new integer by taking the last 2 digits
   of the first and the first 2 digits of the last"
  [p q] (Integer. (apply str (concat (drop 2 (str p)) (take 2 (str q))))))

(def possible-orders (map #(cons octagonals %)
                       (permutations [triangles squares pentagonals hexagonals heptagonals])))

(defn solutions [[ps qs rs good-s? good-t? good-u?]]
  (for [p ps q qs r rs
        :let [s (munge p q) t (munge q r) u (munge r p)]
        :when (and (good-s? s) (good-t? t) (good-u? u))]
    (vector p q r s t u)))

(defn euler061 []
  (loop [o possible-orders]
    (when (seq o)
      (let [s (solutions (first o))]
        (if (empty? s)
          (recur (rest o))
          s)))))
