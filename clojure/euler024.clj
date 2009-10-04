(ns ray1729.projecteuler.euler024)

(defn remove-first [pred? coll]
  (let [remove-first (fn [accum coll]
                       (cond
                         (empty? coll)
                            accum
                         (pred? (first coll))
                            (concat accum (rest coll))
                         :else
                            (recur (conj accum (first coll)) (rest coll))))]
        (remove-first '() coll)))

(defn permutations [elements]
  (let [elements (sort (vec elements))]
    (cond
      (empty? elements) (list)
      (= (count elements) 1) (list elements)
      :else
        (for [a (set elements) p (permutations (remove-first #(= a %) elements))] (conj p a)))))

(defn euler024 []
  (apply str (nth (permutations (range 0 10)) 999999)))