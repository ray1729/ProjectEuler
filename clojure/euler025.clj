(ns ray1729.projecteuler.euler025)

(defn fibo [] (map first (iterate (fn [[a b]] [b (+ a b)]) [1 1])))

(defn euler025 []
  (inc (count (take-while #(< (count (str %)) 1000) (fibo)))))
