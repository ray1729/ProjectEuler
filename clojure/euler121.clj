(ns ray1729.projecteuler.euler121)

(defn P-win-nth-turn [n] (/ 1 (+ n 1)))
(defn P-lose-nth-turn [n] (- 1 (P-win-nth-turn n)))

(defn P-win
  ([n-turns]
    (let [required-wins (inc (int (/ n-turns 2)))]
      (P-win 1 required-wins n-turns)))
  ([turn required-wins remaining-turns]
    (cond
      (zero? required-wins) 1
      (< remaining-turns required-wins) 0
      :else
      (+ (* (P-win-nth-turn turn)
           (P-win (inc turn) (dec required-wins) (dec remaining-turns)))
        (* (P-lose-nth-turn turn)
          (P-win (inc turn) required-wins (dec remaining-turns)))))))

(defn euler121 []
  (let [p (P-win 15)]
    (int (/ 1.0 p))))



