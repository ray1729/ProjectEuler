(ns ray1729.projecteuler.euler040)

(defn integer-to-digits [n]
  (reverse (map #(rem % 10) (take-while #(> % 0) (iterate #(quot % 10) n)))))

(defn digits
  ([] (digits 1))
  ([n] (concat (integer-to-digits n) (lazy-seq (digits (inc n))))))

(defn euler040 []
    (let [digits (digits)] (reduce * (map #(nth digits (dec %)) (take 7 (iterate #(* 10 %) 1))))))