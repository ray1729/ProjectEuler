(ns ray1729.projecteuler.euler056)

(defn integer-to-digits [n]
  (reverse (map #(rem % 10) (take-while #(> % 0) (iterate #(quot % 10) n)))))

(defn digital-sum [n] (reduce + (integer-to-digits n)))

(defn power [a b] (reduce * (repeat a b)))

(defn euler056 []
  (reduce max (for [a (range 1 100) b (range 1 100)] (digital-sum (power a b)))))

