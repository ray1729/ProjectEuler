(ns ray1729.projecteuler.euler051
  (:use [ray1729.clojure.math-extras :only (primes)]))

(defn pow [m n]
  (reduce * (repeat n m)))

(defn n-digit-primes [n]
  (let [minp (pow 10 (dec n)) maxp (* 10 minp)]
    (take-while #(< % maxp) (drop-while #(< % minp) (primes)))))

(defn common-digit-mask [m n]
  (letfn [(d [c1 c2 accum]
            (cond
              (empty? c1) accum
              (= (first c1) (first c2)) (recur (rest c1) (rest c2) (inc (bit-shift-left accum 1)))
              :else (recur (rest c1) (rest c2) (bit-shift-left accum 1))))]
    (d (str m) (str n) 0)))

(defn common-digits? [p q mask]
  (= mask (bit-and mask (common-digit-mask p q))))

(defn repeated-digits? [p mask]
  (let [digits (seq (str p))
        len (count digits)
        wanted (for [i (range 0 len) :when (bit-test mask (dec (- len i)))] (nth digits i))]
    (every? #(= % (first wanted)) (rest wanted))))

(defn in-family? [p q mask]
  (and (common-digits? p q mask) (repeated-digits? q (bit-not mask))))

(defn family [p qs mask]
  (if (not (repeated-digits? p (bit-not mask)))
    '()
    (filter #(in-family? p % mask) qs)))

(defn good-enough? [coll] (> (count coll) 7))

(defn doit [ps mask seen]
  (if (empty? ps) nil
    (let [p (first ps)
          n (count (str p))
          cache (if (seen mask) (seen mask) {})
          toggle (< mask (pow 2 n))
          next-ps (if toggle ps (rest ps))
          next-mask (if toggle (inc mask) 0)]
      (if (contains? cache p) (recur next-ps next-mask seen)
        (let [f (family p ps mask)]
          (cond
            (good-enough? f) f
            (empty? f) (recur next-ps next-mask (assoc seen mask (assoc cache p f)))
            :else (recur next-ps next-mask (assoc seen mask (apply assoc cache (interleave f (repeat f)))))))))))

(defn euler051 [] (doit (n-digit-primes 6) 0 {}))



