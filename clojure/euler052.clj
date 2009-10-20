(ns ray1729.projecteuler.euler052)

(defn euler052 []
  (letfn [(sorted-digits [n]
            (sort (seq (str n))))
          (euler052-recursive [n]
            (let [digits (sorted-digits (* 2 n))]
              (if (every? #(= digits %) (map #(sorted-digits (* n %)) (range 3 7)))
                n
                (recur (inc n)))))]
    (euler052-recursive 1)))
