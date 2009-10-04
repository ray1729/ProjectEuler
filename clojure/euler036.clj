(ns ray1729.projecteuler.euler036)

(defn integer-to-digits
  ([n] (integer-to-digits n 10) )
  ([n base]
    (reverse (map #(rem % base) (take-while #(> % 0) (iterate #(quot % base) n))))))

(defn palindrome?
  ([n] (palindrome? n 10))
  ([n base]
    (let [digits (integer-to-digits n base)]
      (= digits (reverse digits)))))

(defn euler036 []
    (reduce + (filter #(and (palindrome? % 10) (palindrome? % 2)) (range 1 1000000))))
