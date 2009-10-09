(ns ray1729.projecteuler.euler032)

(defn digits-of [n] (seq (str n)))

(defn pandigital-product? [[m n p]]
  (let [mdigits (digits-of m)
        ndigits (digits-of n)
        pdigits (digits-of p) 
         digits (concat mdigits ndigits pdigits)]
	 (and (= 9 (count digits))
	      (let [digits (set digits)]
		   (and (= 9 (count digits))
			(not (digits \0)))))))

; We can use all 9 digits in two ways:
; 1-digit x 4-digit = 4-digit number
; 2-digit x 3-digit = 4-digit number
;
; We could make a small optimizaiton by altering the ranges below to eliminate
; some obviously invalid cases where the starting values contain repeated digits,
; but have opted to keep the code simple.

(defn euler032 []
  (reduce +
    (set
      (map #(get % 2) 
        (filter pandigital-product?
          (concat (for [m (range  2  10) n (range 1000 10000)] [m n (* m n)]) 
		  (for [m (range 10 100) n (range  100  1000)] [m n (* m n)])))))))
