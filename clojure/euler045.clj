(ns ray1729.projecteuler.euler045)

(defn pentagonal? [m]
  (let [r (Math/sqrt (inc (* 24 m)))]
    (and (= r (int r)) (zero? (rem (inc r) 6)))))

(defn hexagonal? [m]
  (let [r (Math/sqrt (inc (* 8 m)))]
    (and (= r (int r)) (zero? (rem (inc r) 4)))))

(defn triangles []
  (map #(/ (* % (inc %)) 2) (iterate inc 1)))

(defn euler045 []
    (nth (filter #(and (pentagonal? %) (hexagonal? %)) (triangles)) 2))