#lang racket
(define (hipercola x k N n)
    (exact->inexact(/ (* (coefc k x) (coefc (- N k)(- n x)))(coefc N n)))
  )

(define (hipersimple x k N n)
    (exact->inexact(/ (* (coefs k x) (coefs (- N k)(- n x)))(coefs N n)))
  )

;;(coefc a b)
;;realiza el coeficiente de hipercola
;;entrega el coeficiente
(define (coefc a b)
  (/ (factorialc a)(* (factorialc b) (factorialc(- a b))))
  )

;;(coefs a b)
;;realiza el coeficiente de hipersimple
;;entrega el coeficiente
(define (coefs a b)
  (/ (factorials a)(* (factorials b) (factorials(- a b))))
  )

;;(factorialc n)
;;calcula el factorial para hipercola
;;entrega el factorial
(define (factorialc n)
  (let fact ((i n) (a 1))
    (if (= i 0)
        a
        (fact (- i 1) (* a i))
        )
    )
  )

;;(factorials n)
;;calcula el factorial para hipersimple
;;entrega el factorial
(define (factorials n)
  (let fact ((i n))
    (if (= i 0)
        1
        (* i (fact (- i 1)))
        )
    )
  )

(hipercola 1 2 4 3)
(hipersimple 1 3 6 4)