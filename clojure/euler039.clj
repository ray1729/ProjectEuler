(ns ray1729.projecteuler.euler039)

(def pythagorean-triangle-perimeters (for [x (range 3 1000)
                                           y (range x 1000)
                                           z (list (Math/sqrt (+ (* x x) (* y y))))
                                           :when (= z (int z))] (+ x y (int z))))

(defn count-unique [accum coll]
  (if (empty? coll) accum
    (let [f (first coll) c (if (accum f) (accum f) 0)] (recur (assoc accum f (inc c)) (rest coll)))))

(def perimeter-counts (count-unique {} (filter #(< % 1000) pythagorean-triangle-perimeters)))

(def max-perimeter-count (apply max (vals perimeter-counts)))

(defn find-val [m v]
  (if (empty? m) nil
    (let [e (first m)]
      (if (= (val e) v) (key e)
        (recur (rest m) v)))))

(defn euler039 []
  (find-val perimeter-counts max-perimeter-count))


