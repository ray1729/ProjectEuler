
(ns ray1729.projecteuler.euler004)

(defn palindrome?
  ([s] (let [chars (re-seq #"." (.toString s))]
         (= chars (reverse chars)))))

(defn euler004
  ([]
    (apply max
      (filter palindrome? (for [a (range 100 1000) b (range a 1000)] (* a b))))))
