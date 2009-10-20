
(ns ray1729.projecteuler.euler004)

(defn palindrome? [s]
  (let [s (str s)]
    (= (seq s) (reverse s))))

(defn euler004 []
  (reduce max
    (filter palindrome? (for [a (range 100 1000) b (range a 1000)] (* a b)))))
