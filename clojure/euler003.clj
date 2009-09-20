(ns ray1729.projecteuler.euler003)

(defn trial-divisors
  ([]
    (let [wheel (cycle [4 2 4 2 4 6 2 6])]
        (concat [2 3 5] (trial-divisors 7 wheel))))
  ([n wheel]
    (lazy-seq
      (cons n (trial-divisors (+ n (first wheel)) (rest wheel))))))

(defn factorize
  ([n]
    (factorize n (trial-divisors)))
  ([n trial-divisors]
    (if (= n 1)
      '()
      (let [d (first trial-divisors) q (quot n d) r (rem n d)]
        (cond
          (zero? r)
            (cons d (lazy-seq (factorize q trial-divisors)))
          (> q d)
            (lazy-seq (factorize n (rest trial-divisors)))
          :else
            (cons n '()))))))

(defn euler003 ([] (last (factorize 600851475143))))
