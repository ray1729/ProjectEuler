(ns ray1729.projecteuler.euler020)

(defn sum-of-digits [n]
  (reduce +
    (map #(rem % 10)
      (take-while #(> % 0)
        (iterate #(quot % 10) n)))))

(defn euler020 []
  (sum-of-digits (reduce * (range 1 100))))
