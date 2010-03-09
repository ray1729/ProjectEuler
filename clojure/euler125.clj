(ns ray1729.projecteuler.euler125)

(def *upper-bound* 100000000)

(defn palindrome? [n] (= (seq (str n)) (reverse (seq (str n)))))

(def squares (take-while #(< % *upper-bound*) (map #(* % %) (iterate inc 1))))

(defn euler125 []
  (reduce + (set (for [psize (range 2 (count squares))
                       psum (take-while #(< % *upper-bound*)
                              (map (partial reduce +) (partition psize 1 squares)))
                       :when (palindrome? psum)]
                   psum))))
