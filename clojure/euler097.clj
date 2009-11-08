(ns ray1729.euler097)

(defn euler097 []
  (let [modulus (.pow (BigInteger. "10") 10)]
    (rem (+ 1 (* 28433 (.modPow (BigInteger. "2") (BigInteger. "7830457") modulus))) modulus)))