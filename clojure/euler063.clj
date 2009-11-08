(ns ray1729.euler063)

; An n-digit number must lie between 10^(n-1) and 10^n; thus, if it is an n-th
; power, we have 10^(n-1) <= x^n < 10^n and therefore:
;
; a) 1 <= x < 10
;
; b) n log_10(x) >= n-1 which, on rearangement gives n <= 1 / ( 1 - log_10(x) )

(defn euler063 []
  (reduce + (map #(int (/ 1 (- 1 (Math/log10 %)))) (range 1 10))))
