#lang racket

;;(i x)
;;hace la multiplicacion a si mismo
;;devuelve el resultado de la multiplicacion
(define i (lambda (x) (* x x)))
;;(p x)
;;hace la multiplicacion entre 2 y el valor
;;devuelve el resultado de la multiplicacion
(define p (lambda (x) (* 2 x)))
(define (multievaluacion i p x)
  (cond
      ((odd? x) (+ (i (i x)) (p x)))
      (else (+ (p (p x)) (i x)))
      )
  )

(multievaluacion i p 3)
(multievaluacion i p 5)