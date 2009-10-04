(ns ray1729.projecteuler.euler027)

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

(defn count-consecutive-primes [f]
  (count (take-while prime? (map f (iterate inc 0)))))

(defn quadratic [a b]
  (fn [n] (+ (* n n) (* a n) b)))

(defn find-max [p n coll]
  (if (empty? coll)
    p
    (let [[a b] (first coll)
          c (count-consecutive-primes (quadratic a b))]
      (if (> c n) 
        (recur (* a b) c (rest coll))
        (recur p n (rest coll))))))

(defn euler027 []
  (find-max 0 0 (for [a (range -1000 1001) b (take-while #(< % 1000) (primes))] [a b])))