(ns ray1729.projecteuler.euler112)

(defn- increasing-recursive? [coll]
  (cond
    (empty? coll) nil
    (= 1 (count coll)) true
    (> (compare (first coll) (first (rest coll))) 0) false
    :else (recur (rest coll))))

(defn increasing? [n] (increasing-recursive? (str n)))

(defn decreasing? [n] (increasing-recursive? (reverse (str n))))

(defn bouncy? [n] (not (or (increasing? n) (decreasing? n))))

(defn find-n-for-bounce-density [d]
  (fn [n b] (if (>= (/ b n) d) n
              (let [m (inc n)]
                (if (bouncy? m) (recur m (inc b)) (recur m b))))))

(defn euler112 [] ((find-n-for-bounce-density 0.99) 1 0))