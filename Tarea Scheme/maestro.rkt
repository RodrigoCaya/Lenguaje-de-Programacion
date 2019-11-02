#lang racket

;;(aux nodo grafo)
;;Cuenta cuantas veces aparece un nodo en el grafo
;;Retorna un contador de la cantidad de ocurrencias
(define (aux nodo grafo)
  (let recursion ( (g grafo) (contador 0) )
    (if (null? g) contador
        (cond
          ((member nodo (car (cdr (car g)))) (recursion (cdr g) (+ contador 1 ) ))
          (else
            (recursion (cdr g) contador )
            )
          )
        )
    )
  )

(define (maestro nodo grafo)
  (cond
    ((not (= (aux nodo grafo) 1 )) #f)
    (else
     (let recursion ( (g grafo) (n (car grafo)) )
       (cond
         ((member nodo (car(cdr n))) (list (car n)))
         (else
          (recursion (cdr g) (car g)))
         )
       )
      )
    )
  )

(define grafo '((1 (2 3)) (2 (3 4)) (3 (4 5)) (4 (2)) (5 (4)) ))
(maestro 3 grafo)