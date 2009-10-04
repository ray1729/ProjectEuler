(ns ray1729.projecteuler.euler038)

(defn integer-to-digits [n]
  (reverse (map #(rem % 10) (take-while #(> % 0) (iterate #(quot % 10) n)))))

(defn digits-to-integer [digits]
  (reduce +
    (map (fn [[a b]] (* a b))
      (partition 2 (interleave (reverse digits) (iterate #(* 10 %) 1))))))

(defn pandigital-product [n]
  (let [pandigital-product (fn [n m s]
                             (if (= (count s) 9) (digits-to-integer s)
                               (let [digits (integer-to-digits (* n m))
                                     sdigits (set digits)]
                                 (cond
                                   (empty? digits) nil
                                   (sdigits 0) nil
                                   (some #(sdigits %) s) nil
                                   (not= (count digits) (count sdigits)) nil
                                   :else (recur n (inc m) (concat s digits))))))]
    (pandigital-product n 1 '())))

(defn euler0038 []
  (apply max (filter identity (map pandigital-product (range 1 9999)))))
