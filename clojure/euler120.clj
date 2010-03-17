(ns ray1729.projecteuler.euler120)

(defn r-max [a] (if (odd? a) (* a (- a 1)) (* a (- a 2))))

(defn euler120 [] (reduce + (map r-max (range 3 1001))))


