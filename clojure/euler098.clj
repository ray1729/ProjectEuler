(ns ray1729.projecteuler.euler098
  ;(:require )
  (:use [clojure.contrib.combinatorics :only (combinations)])
  ;(:import )
  )

(def words (re-seq #"\w+" (slurp "/home/ray/Downloads/words.txt")))

(defn filter-multiple-vals [dict]
  (loop [ks (keys dict) dict dict]
    (if (empty? ks)
      dict
      (let [k (first ks)]
        (if (> (count (get dict k)) 1)
          (recur (rest ks) dict)
          (recur (rest ks) (dissoc dict k)))))))

(defn build-anagram-dict [words]
  (loop [words words dict {}]
    (if (empty? words)
      (filter-multiple-vals dict)
      (let [w (first words) k (sort (seq w)) v (get dict k [])]
        (recur (rest words) (assoc dict k (conj v w)))))))

(def wdict (build-anagram-dict words))

(def sdict (build-anagram-dict (map str (take-while #(< % 1000000000) (map #(* % %) (iterate inc 1))))))

(defn pairs [dict]
  (for [coll (vals dict) pair (combinations coll 2)] pair))

(def word-pairs (pairs wdict))

(def square-pairs (pairs sdict))

(defn mktransform [s1 s2]
  (fn [s]
    (let [m (zipmap (seq (str s1)) (seq (str s)))]
      (apply str (map m (seq (str s2)))))))

(defn find-square-anagrams [word-pairs]
  (when (seq word-pairs)
    (let [wp (first word-pairs) tr (apply mktransform wp)]
      (lazy-cat (filter (fn [[s1 s2]] (and (= (count (set (seq s1))) (count (set (seq (first wp)))))
                                        (or (= s1 (tr s2)) (= s2 (tr s1)))))
                square-pairs)
        (find-square-anagrams (rest word-pairs))))
    ))

(defn euler098 []
  (reduce max (map (fn [coll] (reduce max (map #(BigInteger. %) coll))) (find-square-anagrams word-pairs))))

; this can be simplified by doing away with the square-pairs list and, working with a pre-computed
; set of squares, test whether, for each square, tr(square) or tr-inv(square) is a square

(def squares (map str (take-while #(< % 1000000000) (map #(* % %) (iterate inc 1)))))

(defn square-anagrams [word-pairs]
  (when (seq word-pairs)
    (let [wp (first word-pairs)
          wl (count (set (first wp)))
          dwl (count (set (seq (first wp))))
          tr (apply mktransform wp)
          tr-inv (apply mktransform (reverse wp))
          sq (set (filter #(and (= wl (count (seq %))) (= dwl (count (set (seq %))))) squares))]
      (lazy-cat (filter #(or (sq (tr %)) (sq (tr-inv %))) sq) (square-anagrams (rest word-pairs))))))

(defn euler098a [] (reduce max (map #(BigInteger. %) (square-anagrams word-pairs))))