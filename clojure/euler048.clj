(ns ray1729.projecteuler.euler048)

(defn nth-power-of-n [n]
  (reduce #(rem (* %1 %2) 10000000000) (repeat n n)))

(defn euler048 []
    (rem (reduce + (map nth-power-of-n (range 1 1000))) 10000000000))
