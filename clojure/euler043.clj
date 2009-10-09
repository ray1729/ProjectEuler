(ns ray1729.projecteuler.euler043)

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

(defn to-integer [digits] (BigInteger. (apply str digits)))

(defn interesting? [digits]
  (let [digits (vec digits)]
       (and (not (zero? (first digits)))
	    (every? zero? (map (fn [[o p]] (rem (to-integer (subvec digits o (+ 3 o))) p))
			       (list [1 2] [2 3] [3 5] [4 7] [5 11] [6 13] [7 17]))))))

(defn euler043 []
  (reduce + (map to-integer (filter interesting? (permutations (range 0 10))))))

