(ns ray1729.euler055)

(defn integer-to-digits [n]
  (reverse (map #(rem % 10) (take-while #(> % 0) (iterate #(quot % 10) n)))))

(defn digits-to-integer [digits]
  (reduce +
    (map (fn [[a b]] (* a b))
      (partition 2 (interleave (reverse digits) (iterate #(* 10 %) 1))))))

(defn palindrome? [n]
  (let [digits (integer-to-digits n)]
    (= digits (reverse digits))))

(defn lychrel?
  ([n] (lychrel? n 0))
  ([n iter]
    (let [reverse-n (digits-to-integer (reverse (integer-to-digits n)))
          n-plus-reverse-n (+ n reverse-n)]
      (cond
        (>= iter 50) true
        (palindrome? n-plus-reverse-n) false
        :else (recur n-plus-reverse-n (inc iter))))))

(defn euler055 [] (count (filter lychrel? (range 1 10000))))




