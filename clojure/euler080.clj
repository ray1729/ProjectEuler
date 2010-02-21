(ns ray1729.projecteuler.euler080)

(def square? (apply hash-set (map #(* % %) (range 1 11))))

(defn digits-to-integer [digits]
  (reduce +
    (map (fn [[a b]] (* a b))
      (partition 2 (interleave (reverse digits) (iterate #(* 10 %) 1))))))

(defn integer-to-digits [n]
  (reverse (map #(rem % 10) (take-while #(> % 0) (iterate #(quot % 10) n)))))

(defn digit-seq [n]
  (concat (if (even? (int (Math/log10 n))) [0]) (integer-to-digits n) (repeat 0)))

(defn next-x [p c]
  "find the largest digit, x, such that (20*p + x) * x <= c"
  (letfn [(guess [x]
            (let [y (* x (+ x (* 20 p)))]
              (if (> y c) (recur (dec x)) x)))]
    (guess 9)))

(defn square-root-digits
  "Compute the decimal digits of sqrt(n). See <http://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Digit_by_digit_calculation> for a description of the algorithm"
  ([n]
    (let [digit-pairs (map digits-to-integer (partition 2 (digit-seq n)))]
      (square-root-digits 0 (first digit-pairs) (rest digit-pairs))))
  ([p c ps]
   (let [x (next-x p c)
         y (* (+ (* 20 p) x) x)
         r (- c y)]
     (lazy-seq (cons x (square-root-digits (+ (* p 10) x) (+ (* 100 r) (first ps)) (rest ps)))))))

(defn euler080 []
  (reduce +
    (map #(reduce + (take 100 (square-root-digits %)))
        (filter #(not (square? %)) (range 1 100)))))