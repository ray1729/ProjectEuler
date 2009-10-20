(ns ray1729.euler053)

(defn- factorial [n] (reduce * (range 1 (inc n))))

(defn- n-choose-r [n r] (/ (factorial n) (* (factorial r) (factorial (- n r)))))

(defn euler053 []
  (reduce +
    (for [n (range 1 101) r (range 1 n) :when (> (n-choose-r n r) 1000000)] 1)))