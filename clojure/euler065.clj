(ns ray1729.projecteuler.euler065)

(defn terms []
  (letfn [(t [n] (let [q (quot n 3) r (rem n 3)]
		   (if (= r 1) (* 2 (inc q)) 1)))]
    (cons 2 (map t (iterate inc 0)))))
    
(defn nth-convergent [n]
  (let [ts (reverse (take n (terms)))]
    (reduce (fn [a b] (+ (/ 1 a) b)) ts)))

(defn integer-to-digits [n]
  (reverse (map #(rem % 10) (take-while #(> % 0) (iterate #(quot % 10) n)))))

(defn euler065 []
  (reduce + (integer-to-digits (.numerator (nth-convergent 100)))))