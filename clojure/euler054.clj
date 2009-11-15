(ns ray1729.projecteuler.euler054
 (:use [clojure.contrib.duck-streams :only (reader)])

(defstruct card :rank :suit)

(defn create-card [s]
  (let [[rank suit] (re-seq #"\w" s)]
    (struct card rank suit)))

(defn value-of-card [c]
  (let [ranks ["2" "3" "4" "5" "6" "7" "8" "9" "T" "J" "Q" "K" "A"]
        rank-to-value (apply hash-map (interleave ranks (iterate inc 2)))]
    (rank-to-value (:rank c))))

(defn create-hand [s]
  (map create-card (re-seq #"\w+" s)))

(defn group-by-value [cards]
  (letfn [(group-by-value [cards accum]
            (if (empty? cards)
              accum
              (let [f (first cards) v (value-of-card f)]
                (if (accum v)
                  (recur (rest cards) (assoc accum v (conj (accum v) f)))
                  (recur (rest cards) (assoc accum v (list f)))))))]
    (group-by-value cards {})))

(defn n-tuples [cards n]
  (filter #(= n (count %)) (vals (group-by-value cards))))

(defn hcv [cards] (apply max (map value-of-card cards)))

(defn flush? [cards]
  (every? #(= (:suit (first cards)) %) (map :suit (rest cards))))

(defn straight? [cards]
  (every? (fn [[a b]] (= b (inc a))) (partition 2 1 (sort (map value-of-card cards)))))

(defn straight-flush? [cards]
  (and (straight? cards) (flush? cards)))

(defn royal-flush? [cards]
  (and (straight-flush? cards) (= 14 (hcv cards))))

(defn one-pair? [cards]
  (= 1 (count (n-tuples cards 2))))

(defn two-pairs? [cards]
  (= 2 (count (n-tuples cards 2))))

(defn three-of-a-kind? [cards]
  (= 1 (count (n-tuples cards 3))))

(defn four-of-a-kind? [cards]
  (= 1 (count (n-tuples cards 4))))

(defn full-house? [cards]
  (and (one-pair? cards) (three-of-a-kind? cards)))

(defn compare-hcv [h1 h2]
  (apply compare (map hcv (list h1 h2))))

(defn compare-three-of-a-kind [h1 h2]
  (cond
    (three-of-a-kind? h1) (if (three-of-a-kind? h2)
                            (let [t1 (n-tuples h1 3)
                                  t2 (n-tuples h2 3)]
                              (apply compare (map value-of-card (list (ffirst t1) (ffirst t2)))))
                             1)
    (three-of-a-kind? h2) -1
    :else 0))

(defn compare-two-pairs [h1 h2]
  (cond
    (two-pairs? h1) (if (two-pairs? h2)
                      (let [p1 (map #(value-of-card (first %)) (n-tuples h1 2))
                            p2 (map #(value-of-card (first %)) (n-tuples h2 2))
                            c1 (compare (apply max p1) (apply max p2))]
                        (if (zero? c1) (compare (apply min p1) (apply min p2)) c1))
                      1)
    (two-pairs? h2) -1
    :else 0))

(defn compare-one-pair [h1 h2]
  (cond
    (one-pair? h1) (if (one-pair? h2)
                     (let [p1 (first (n-tuples h1 2))
                           p2 (first (n-tuples h2 2))]
                       (apply compare (map #(value-of-card (first %)) (list p1 p2))))
                     1)
    (one-pair? h2) -1
    :else 0))

(defn compare-four-of-a-kind [h1 h2]
  (cond
    (four-of-a-kind? h1) (if (four-of-a-kind? h2)
                           (let [t1 (n-tuples h1 4) t2 (n-tuples h2 4)
                                 v1 (value-of-card (ffirst t1))
                                 v2 (value-of-card (ffirst t2))]
                             (compare v1 v2))
                           1)
    (four-of-a-kind? h2) -1
    :else 0))

(defn compare-royal-flush [h1 h2]
  (cond
    (royal-flush? h1) (if (royal-flush? h2) 0 1)
    (royal-flush? h2) -1
    :else 0))

(defn compare-straight-flush [h1 h2]
  (cond
    (straight-flush? h1) (if (straight-flush? h2) (compare-hcv h1 h2) 1)
    (straight-flush? h2) -1
    :else 0))

(defn compare-full-house [h1 h2]
  (cond
    (full-house? h1) (if (full-house? h2)
                       (let [c3 (compare-three-of-a-kind h1 h2)]
                         (if (zero? c3) (compare-one-pair h1 h2) c3))
                       1)
    (full-house? h2) -1
    :else 0))

(defn compare-flush [h1 h2]
  (cond
    (flush? h1) (if (flush? h2) (compare-hcv h1 h2) 1)
    (flush? h2) -1
    :else 0))

(defn compare-straight [h1 h2]
  (cond
    (straight? h1) (if (straight? h2) (compare-hcv h1 h2) 1)
    (straight? h2) -1
    :else 0))

(defn compare-hands [h1 h2]
  (letfn [(compare-hands-with [comparators]
            (let [c ((first comparators) h1 h2)]
              (if (zero? c) (recur (rest comparators)) c)))]
    (compare-hands-with (list compare-royal-flush
                          compare-straight-flush
                          compare-four-of-a-kind
                          compare-full-house
                          compare-flush
                          compare-straight
                          compare-three-of-a-kind
                          compare-two-pairs
                          compare-one-pair
                          compare-hcv))))

 (defn parse-hands [l]
   (let [cards (create-hand l)]
     [(take 5 cards) (drop 5 cards)]))

 (defn get-hands [filename]
   (with-open [rdr (reader filename)]
     (doall (map parse-hands (line-seq rdr)))))

 (def hands (get-hands "/home/ray/Downloads/poker.txt"))

 (defn find-winners [hands accum]
  (if (empty? hands) accum
    (let [[h1 h2] (first hands) c (compare-hands h1 h2)]
      (recur (rest hands) (assoc accum c (conj (accum c) (first hands)))))))
 
(defn euler054 [filename]
  (let [hands (get-hands filename)
        winners (find-winners hands {-1 [], 0 [], 1 []})]
    (count (get winners 1))))

