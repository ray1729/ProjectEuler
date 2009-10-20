(ns ray1729.projecteuler.euler035)

; This implementation of the sieve of Eratosthenes by Christophe Grand,
; http://clj-me.cgrand.net/index.php?s=Everybody loves the Sieve of Eratosthenes

(defn primes []
  (letfn [(enqueue [sieve n step]
            (let [m (+ n step)]
              (if (sieve m)
                (recur sieve m step)
                (assoc sieve m step))))
          (next-sieve [sieve candidate]
            (if-let [step (sieve candidate)]
              (-> sieve
                (dissoc candidate)
                (enqueue candidate step))
              (enqueue sieve candidate (+ candidate candidate))))
          (next-primes [sieve candidate]
            (if (sieve candidate)
              (recur (next-sieve sieve candidate) (+ candidate 2))
              (cons candidate
                (lazy-seq (next-primes (next-sieve sieve candidate)
                            (+ candidate 2))))))]
    (cons 2 (lazy-seq (next-primes {} 3)))))

(defn prime? [n]
  (and (> n 1)
    (let [b (int (Math/sqrt n))]
        (not-any? zero? (map #(rem n %) (take-while #(<= % b) (primes)))))))

(defn integer-to-digits [n]
  (reverse (map #(rem % 10) (take-while #(> % 0) (iterate #(quot % 10) n)))))

(defn digits-to-integer [digits]
  (reduce +
    (map (fn [[a b]] (* a b))
      (partition 2 (interleave (reverse digits) (iterate #(* 10 %) 1))))))

(defn digits-to-integer2 [digits]
  (reduce +
    (map (fn [[a b]] (* a b))
      (map vector (reverse digits) (iterate #(* 10 %) 1)))))

(defn digits-to-integer3 [digits]
  (reduce +
    (map #(* %1 %2) (reverse digits) (iterate #(* 10 %) 1))))

(defn rotate [coll]
  (conj (take (dec (count coll)) coll) (last coll)))

(defn rotations [coll]
  (take (count coll) (iterate rotate coll)))

(defn circular-prime? [p]
  (every? prime? (map digits-to-integer (rotations (integer-to-digits p)))))

(defn euler035 []
  (count (filter circular-prime? (take-while #(< % 1000000) (primes)))))
