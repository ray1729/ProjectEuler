(ns ray1729.projecteuler.euler074)

(defn factorial [n] (reduce * (range 1 (inc n))))

(def fdigit (apply hash-map
              (interleave
                (str "0123456789")
                (map factorial (range 0 10)))))

(defn sum-factorial-digits [n] (reduce + (map fdigit (str n))))

(defn chain-sum-factorial-digits [n]
  (letfn [(chain-sum-factorial-digits [n coll]
            (let [m (sum-factorial-digits n)]
              (if (some #(= m %) coll) coll (recur m (conj coll m)))))]
    (chain-sum-factorial-digits n [n])))

(defn euler074 []
  (count (filter #(= 60 %) (map #(count (chain-sum-factorial-digits %)) (range 1 1000000)))))

; Can we optimize by utilizing values we've already computed?
; XXX What follows is far too complex and not measurably faster; need to find a
; way to compute (chain-length n) as (inc (chain-length (sum-factorial-digits n)))
; without blowing the stack; we would then benefit from a memoized version of
; chain-length.

(def cache (atom {}))

(defn chain-length [n]
  (if (@cache n) (@cache n)
    (let [chain (chain-sum-factorial-digits n)
          repeater (sum-factorial-digits (last chain))
          [pre-repeat post-repeat] (split-with #(not= % repeater) chain)]
      (swap! cache (partial apply assoc)
        (concat
          (interleave pre-repeat (iterate dec (count chain)))
          (interleave post-repeat (repeat (count post-repeat)))))
      (count chain))))

(defn euler074-faster []
  (count (filter #(= % 60) (map chain-length (range 1 1000000)))))


