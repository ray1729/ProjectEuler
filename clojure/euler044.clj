(ns ray1729.projecteuler.euler044)

(defn pentagonal? [m]
  (let [r (Math/sqrt (inc (* 24 m)))]
    (and (= r (int r)) (zero? (rem (inc r) 6)))))

(defn pentagonals []
  (map #(/ (* % (dec (* 3 %))) 2) (iterate inc 1)))

(defn euler044 []
  (first
    (for [j (pentagonals)
          k (take-while #(< % j) (pentagonals))
        :when (and (pentagonal? (+ j k)) (pentagonal? (- j k)))] (- j k))))
