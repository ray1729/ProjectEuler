(ns ray1729.projecteuler.euler002)

(defn even-fibonacci
  ([]
    (cons 2 (even-fibonacci 1 1 2)))
  ([a b c]
    (lazy-seq
      (let [a (+ b c) b (+ a c) c (+ a b) ]
            (cons c (even-fibonacci a b c))))))

(defn sum-even-fibonacci
  ([n]
    (reduce + (take-while #(<= % n) (even-fibonacci)))))

(defn euler002 ([] (sum-even-fibonacci 4000000)))
