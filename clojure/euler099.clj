(ns ray1729.projecteuler.euler099
  ;(:require )
  (:use [clojure.contrib.duck-streams :only [read-lines])
  ;(:import )
  )

(defn parse-line [s]
  (map #(BigInteger. %) (re-seq #"\d+" s)))

(def input-values (for [[base exp] (map parse-line (read-lines "/home/ray/Downloads/base_exp.txt"))]
                     (* exp (Math/log base))))

(defn find-max [coll n best-n best-v]
  (cond
    (empty? coll) best-n
    (> (first coll) best-v) (recur (rest coll) (inc n) n (first coll))
    :else                   (recur (rest coll) (inc n) best-n best-v)))

(defn euler099 [] (find-max input-values 1 0 0))