#lang racket


;;(pares union)
;;separa en una lista todos los pares de la original
;;devuelve una lista con todos los pares
(define (pares union)
  (let recursion ((final '()) (aux union))
    (cond
      ((null? aux) final)
      ((even? (car aux)) (recursion (append final (list (car aux))) (cdr aux)))
      (else (recursion final (cdr aux))))))
      
;;(impares union)
;;separa en una lista todos los impares de la original
;;devuelve una lista con todos los impares
(define (impares union)
  (let recursion ((final '()) (aux union))
    (cond
      ((null? aux) final)
      ((odd? (car aux)) (recursion (append final (list (car aux))) (cdr aux)))
      (else (recursion final (cdr aux))))))

(define (conga lista lista1)
  (let recursion ((final '()) (aux (pares (append lista lista1))) (aux1 (impares (append lista lista1))))
    (cond
      ((null? aux) final)
      (else
       (set! final (append final (list (car aux) (car aux1))))
       (recursion final (cdr aux) (cdr aux1)))
      )
    )
  )

(conga '(4 5 6 7 8) '(4 5 6 7 9))