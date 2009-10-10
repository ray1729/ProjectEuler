(ns ray1729.projecteuler.euler049 (:use [ray1729.clojure.math-extras :only (primes prime?)]))

(defn digital-permutations? [n & ns]
  (let [sorted-digits (fn [n] (sort (str n)))
        n-digits (sorted-digits n)]
    (every? #(= n-digits (sorted-digits %)) ns)))

(defn euler049 []
  (for [n (take-while #(< % 10000) (drop-while #(< % 1000) (primes)))
        m (range 1 (/ (- 10000 n) 2))
        :when (and (prime? (+ n m))
                (prime? (+ n m m))
                (digital-permutations? n (+ n m) (+ n m m)))]
    (list n (+ n m) (+ n m m))))

; Improved (faster) solution after reading the problem thread. Build dictionary
; of primes that are anagrams of each other, and search each list for an
; arithmetic progression of at least 3 elements

(defn build-anagram-dict [words]
    (apply merge-with concat (map #(hash-map (sort (str %)) (list %)) words)))

(def prime-anagrams
  (build-anagram-dict
    (take-while #(< % 10000)
      (drop-while #(< % 1000) (primes)))))

; XXX find arithmetic progressions

(filter #(> (count %) 2) (vals prime-anagrams))