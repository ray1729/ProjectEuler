(ns ray1729.euler057)

(defn continued-fraction-sqrt-2 []
  (map inc (iterate #(/ 1 (+ 2 %)) (/ 1 2))))

(defn wanted? [ratio]
  (letfn [(ndigits [n] (count (str n)))]
    (> (ndigits (.numerator ratio)) (ndigits (.denominator ratio)))))

(defn euler057 []
  (count (filter wanted? (take 1000 (continued-fraction-sqrt-2)))))