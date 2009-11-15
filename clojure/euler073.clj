(ns ray1729.projecteuler.euler073
  (:use [clojure.contrib.math :only (gcd)]))

(defn coprime? [m n] (= 1 (gcd m n)))

(defn num-fracs [denom]
  (let [min-num (int (Math/ceil (/ denom 3)))
        max-num (int (Math/floor (/ denom 2)))]
    (count (filter #(coprime? % denom) (range min-num (inc max-num))))))

(defn euler073 [] (reduce + (map num-fracs (range 4 12001))))