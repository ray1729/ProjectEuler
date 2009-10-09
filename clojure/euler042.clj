(ns ray1729.projecteuler.euler042)

(defn is-triangle? [n]
  (let [n (* 2 n) m (int (Math/sqrt n))] (= n (* m (inc m)))))

;(let [valueof (apply hash-map (interleave (re-seq #"\w" "ABCDEFGHIJKLMNOPQRSTUVWXYZ") (range 1 27)))]
;  (defn word-value [w]
;    (reduce + (map valueof (re-seq #"\w" w)))))

;(defn word-value [w]
;  (reduce + (map #(- (inc (int (.charAt w %))) (int \A)) (range 0 (count w)))))

; The above two definitions both work, but I think the following is simpler:
(defn word-value [w]
  (reduce + (map #(inc (- (int %) (int \A))) w)))

(defn euler042 [path]
  (count (filter is-triangle? (map word-value (re-seq #"\w+" (slurp path))))))
