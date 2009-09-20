
(ns ray1729.projecteuler.euler005
  (:use [clojure.contrib.math :only (lcm)]))

(defn euler005 ([] (reduce lcm (range 1 21))))



