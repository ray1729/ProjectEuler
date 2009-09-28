; In England the currency is made up of pound, £, and pence, p, and there are
; eight coins in general circulation:
;
;    1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
;
; It is possible to make £2 in the following way:
;
;   1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
;
; How many different ways can £2 be made using any number of coins?"

(ns ray1729.projecteuler.euler031)

; This solution works by observing that the number of solutions is equal
; to the number of solutions that include the first coin plus the number
; of solutions that do not include the first coin.  The number of solutions
; that *do* include the first coin is given by recuring on the amount less the
; value of the first coin.
;
; N.B. This solution is not tail-recursive, so will blow up on large values
; of n.

(defn N [amount coins]
  (cond
    (empty? coins) 0
    (zero? amount) 1
    (> (first coins) amount) (N amount (rest coins))
    :else (+ (N (- amount (first coins)) coins) (N amount (rest coins)))))

(def coins (list 200 100 50 20 10 5 2 1))

(defn euler031 [] (N 200 coins))
