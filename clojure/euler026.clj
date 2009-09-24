(ns ray1729.projecteuler.euler026)

; When the denominator of a fraction m/n has the form n=n_0 2^alpha 5^beta with (n_0,10)=1,
; then the period begins after max(alpha,beta) terms and the length of the period is the
; exponent to which 10 belongs (mod n_0), i.e., the number x such that 10^x=1 (mod n_0).
; <http://mathworld.wolfram.com/DecimalExpansion.html>

(defn multiplicative-order [n m]
  "Computes the multiplicative order of n modulo m"
  (inc (count (take-while #(not (== 1 %)) (iterate #(rem (* n %) m) n)))))

(defn decimal-period-length [n]
  "Computes length of the period of the decimal expansion of 1/n"
  (cond
    (== 1 n) 0
    (zero? (rem n 2)) (recur (/ n 2))
    (zero? (rem n 5)) (recur (/ n 5))
    :else (multiplicative-order 10 n)))

(defn euler026 []
    (let [period-lengths (map decimal-period-length (range 1 1000))
          max-period-length (apply max period-lengths)]
      (inc (count (take-while #(< % max-period-length) period-lengths)))))
