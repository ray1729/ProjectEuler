(ns ray1729.projecteuler.euler016)

(defn sum-of-digits [n]
  (reduce +
    (map #(rem % 10)
      (take-while #(> % 0)
        (iterate #(quot % 10) n)))))

(sum-of-digits (bit-shift-left 2 999))
