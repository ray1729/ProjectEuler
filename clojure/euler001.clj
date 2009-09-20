(ns ray1729.projecteuler.euler001
    (:use [clojure.contrib.math :only (lcm)]))

(defn sum
  ([n]
    (/ (* n (inc n)) 2))
  ([n step]
    (* step (sum (quot n step))))
  ([n step1 step2]
    (- (+ (sum n step1) (sum n step2)) (sum n (lcm step1 step2)))))

(defn euler001 ([] (sum 999 3 5)))
