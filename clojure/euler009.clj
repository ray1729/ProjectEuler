(ns ray1729.projecteuler.euler009)

(defn pythagorean-triple?
  ([a b c] (= (+ (* a a) (* b b)) (* c c))))

(defn euler009
  ([] (reduce * (first (filter #(apply pythagorean-triple? %)
        (for [a (range 1 333) b (range (inc a) (/ (- 1000 a) 2))] [a b (- 1000 a b)]))))))

