(ns ray1729.projecteuler.euler071)

(defn euler071-recursive [d best]
  (if (> d 1000001)
    best
    (let [n (int (/ (* 3 d) 7)) v (/ n d)]
      (if (and (< v (/ 3 7)) (> v best))
        (recur (inc d) v)
        (recur (inc d) best)))))

(defn euler071 [] (euler071-recursive 1 0))

