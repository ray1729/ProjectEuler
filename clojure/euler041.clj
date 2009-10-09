(ns ray1729.projecteuler.euler041)


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

(defn digits-to-integer [digits]
  (reduce +
    (map (fn [[a b]] (* a b))
      (partition 2 (interleave (reverse digits) (iterate #(* 10 %) 1))))))

; Observe that sum(1..9) = 45 and sum(1..8) = 36, so any number containing only
; the digits 1-9 or 1-8 will be divisible by 3.  Hence we can begin by considering
; numbers made from the digits 1-7.

(defn euler041 []
    (first (filter prime? (map digits-to-integer (reverse (permutations (range 1 8)))))))
