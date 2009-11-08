(ns ray1729.projecteuler.euler069
  ;(:require )
  (:use ray1729.clojure.math-extras :only [factorize])
  ;(:import )
  )

(defn factor-map [n]
  (letfn [(factor-map [factors m]
            (if (empty? factors) m
              (let [f (first factors)]
                (if (m f)
                  (recur (rest factors) (assoc m f (inc (m f))))
                  (recur (rest factors) (assoc m f 1))))))]
    (factor-map (factorize n) {})))

(defn totient [n]
  (letfn [(term [entry]
            (let [p (key entry) k (val entry)] (* (dec p) (Math/pow p (dec k)))))]
    (reduce * (map term (factor-map n)))))

(defn solve-recursively [n best best-r]
  (if (> n 1000000) best
    (let [r (/ n (totient n))]
      (if (> r best-r) (recur (inc n) n r) (recur (inc n) best best-r)))))

(defn euler069 [] (solve-recursively 1 0 0))

; We already used the fact that, if n = prod(p_i^k_i) then
; phi(n) = prod( (p_i - 1)p_i^(k_i - 1). If we then observe that
; n/phi(n) = prod( p_i/(p_i - 1), we can simplify the above:

(defn n-over-phi-n [n]
  (reduce * (map #(/ % (dec %)) (set (factorize n)))))

(defn solve-recursively [n best best-r]
  (if (> n 1000000) best
    (let [r (n-over-phi-n n)]
      (if (> r best-r) (recur (inc n) n r) (recur (inc n) best best-r)))))

(defn euler069 [] (solve-recursively 1 0 0))
