(ns ray1729.projecteuler.euler206)

(def min-guess (int (Math/sqrt 1020304050607080900)))

(defn is-solution? [n] (re-matches #"^1.2.3.4.5.6.7.8.9.0$" (str (* n n))))

; The square ends in 0, so must be a square of a multiple of 10. min-guess is
; a multiple of 10, so we can increment in multiples of 10. A shame we didn't
; thing to divide by 100 and find a solution to 1_2_3_4_5_6_7_8_9 which, ending
; in 9, must be the square of a number ending in 3 or 7.

(defn euler206 []
  (int (Math/sqrt (BigInteger. (some is-solution? (iterate #(+ % 10) min-guess))))))
