(ns ray1729.projecteuler.euler033)

; 10a + b   a
; ------- = - => c(10a + b) = a(10b + c)
; 10b + c   c
;
; a/c < 1 => a < c
;
; (10a+b)/(10b+c) < 1 => (10a+b) < (10b+c)

(reduce * (for [a (range 1 10) b (range 1 10) c (range a 10)
      	       :when (and (< (+ (* 10 a) b) 
               	             (+ (* 10 b) c))
                 	  (= (* c (+ (* 10 a) b)) 
                    	     (* a (+ (* 10 b) c))))] 
			     (/ a c)))

