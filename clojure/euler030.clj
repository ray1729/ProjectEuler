(ns ray1729.projecteuler.euler030)

; Observe that the maximum sum of 5th powers of an n-digit number is
; n*9^5 (when all the digits are 9's). The smallest 7-digit number,
; 1000000, is greater than 7*9^5 = 413343, so a number that is equal
; to the sum of 5th powers of its digits can contain at most 6 digits.
; Hence an upper bound for our search is 6*9^5 = 354294.

(defn digits [n]
  (map #(rem % 10) (take-while #(> % 0) (iterate #(quot % 10) n))))

(defn sum-5th-power-of-digits [n]
  (reduce + (map #(Math/pow % 5) (digits n))))

(defn euler030 []
  (reduce + (filter #(= % (sum-5th-power-of-digits %)) (range 2 354295))))
