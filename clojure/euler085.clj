(ns ray1729.projecteuler.euler085)

(defn num-rectangles [n m]
  (letfn [(num-rectangles-of-size [a b] (* (- n a) (- m b)))]
    (reduce + (for [a (range 0 n) b (range 0 m)] (num-rectangles-of-size a b)))))

(defn abs [n] (if (pos? n) n (- n)))

(defn solve-recursively [m n best-val best-area]
  (if (= 2000 m) [best-val best-area]
    (let [nr (num-rectangles m n)
          toggle (> nr 2000000)
          next-m (if toggle (inc m) m)
          next-n (if toggle 1 (inc n))]
      (if (< (abs (- 2000000 nr)) (abs (- 2000000 best-val)))
        (recur next-m next-n nr (* n m))
        (recur next-m next-n best-val best-area)))))

(defn euler085 [] (solve-recursively 1 1 0 0))




