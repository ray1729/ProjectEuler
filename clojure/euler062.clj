(ns ray1729.projecteuler.euler062
  (:use ['clojure.contrib.combinatorics :only (permutations)]))

(defn cube [n] (* n n n))

(defn integer-to-digits [n]
  (reverse (map #(rem % 10) (take-while #(> % 0) (iterate #(quot % 10) n)))))

(defn collect-anagrams-of-cubes [n last-ds accum]
  (let [c (cube n) ds (sort (integer-to-digits c))]
    (if (> (count ds) (count last-ds)) (recur (inc n) ds {ds [c]})
      (let [next-accum (if (accum ds)
                         (assoc accum ds (conj (accum ds) c))
                         (assoc accum ds [c]))]
        (if (= 5 (count (next-accum ds)))
          (next-accum ds)
          (recur (inc n) ds next-accum))))))

(defn euler062 [] (first (collect-anagrams-of-cubes 0 '() {})))