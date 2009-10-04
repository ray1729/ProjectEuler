(ns ray1729.projecteuler.euler034)

(defn digits [n]
  (map #(rem % 10) (take-while #(> % 0) (iterate #(quot % 10) n))))

(defn factorial [n] (reduce * (range 1 (inc n))))

(defn sum-factorial-of-digits [n]
  (reduce + (map #(factorial %) (digits n))))

(defn euler034 []
  (reduce + (filter #(= % (sum-factorial-of-digits %)) (range 3 (inc (* 7 (factorial 9)))))))
