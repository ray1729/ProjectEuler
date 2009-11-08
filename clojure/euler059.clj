(ns ray1729.euler059
 (:use [clojure.contrib.generic.math-functions :only (abs)]))

(defn crypt-xor [coll password]
  (let [pass-seq (cycle (map int (seq password)))]
    (map bit-xor coll pass-seq)))

(defn char-freq [coll]
  (letfn [(lower [c]
            (let [v (int c)]
              (if (and (>= v (int \A)) (<= v (int \Z))) (char (+ v 32)) c)))
          (char-freq [coll freq]
            (if (empty? coll)
            (let [n (double (reduce + (vals freq)))]
              (apply hash-map (interleave (keys freq) (map #(/ % n) (vals freq)))))
            (let [c (lower (first coll))]
              (recur (rest coll) (assoc freq c (if (contains? freq c)
                                                 (inc (get freq c))
                                                 1))))))]
      (char-freq coll {})))

(def ideal-freq (apply hash-map (interleave "abcdefghijklmnopqrstuvwxyz "
                                            (list 0.0651738 0.0124248 0.0217339 0.0349835 0.1041442 0.0197881 0.0158610 0.0492888 0.0558094 0.0009033 0.0050529 0.0331490 0.0202124 0.0564513 0.0596302 0.0137645 0.0008606 0.0497563 0.0515760 0.0729357 0.0225134 0.0082903 0.0171272 0.0013692 0.0145984 0.0007836 0.1918182))))

(defn distance-from-ideal [coll]
  (let [freq (char-freq coll)]
    (reduce + (map #(abs (- (get freq % 0) (get ideal-freq % 0))) (set (concat (keys ideal-freq) (keys freq)))))))

(def ciphertext (map #(Integer. %) (re-seq #"\d+" (slurp "/home/ray/Downloads/cipher1.txt"))))

(def trial-passwords (let [pwchars "abcdefghijklmnopqrstuvwxyz"]
                       (for [a pwchars b pwchars c pwchars] [a b c])))

(defn tryit [min-distance value trial-passwords]
  (let [pw (first trial-passwords)
        pt (crypt-xor ciphertext pw)
        d  (distance-from-ideal (map char pt))]
    (if (< d min-distance)
      (recur d (reduce + pt) (rest trial-passwords))
      (recur min-distance value (rest trial-passwords)))))
