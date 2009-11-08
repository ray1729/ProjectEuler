(ns ray1729.euler092)

(defn sum-of-squares-of-digits [n]
  (let [digits (map #(- (int %) (int \0)) (seq (str n)))]
    (reduce + (map #(* % %) digits))))

(defn sum-of-squares-of-digits-recur [n]
  (let [s (sum-of-squares-of-digits n)]
    (if (or (= s 1) (= s 89)) s (recur s))))

; slow, but works
;(defn euler092 []
;  (count (filter #(= 89 %) (map #(sum-of-squares-of-digits-recur %) (range 1 10000000)))))

; improvement (after reading solution thread)
(def sum-of-squares-of-digits-lookup
  (apply hash-map (interleave (range 1 568) (map sum-of-squares-of-digits-recur (range 1 568)))))

(defn wanted? [n] (= 89 (sum-of-squares-of-digits-lookup (sum-of-squares-of-digits n))))

(defn euler092 []
  (count (filter wanted? (range 1 10000000))))