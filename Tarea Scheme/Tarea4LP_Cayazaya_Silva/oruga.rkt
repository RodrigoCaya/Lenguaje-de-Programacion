#lang racket
;;ESTE ARCHIVO FUNCIONA SOLO SI SE OCUPA LA FORMA DE LOS EJEMPLOS
;;(oruga '(O '(1 1 1 1 1 1)))
;;LA FORMA DEBE SER (oruga '(LETRA '(LISTA)))


;;(AND LISTA)
;;Revisa si el valor de verdad al comparar con un AND los elementos de la lista
;;Devuelve una lista con 1 y 0 dependiendo del valor de verdad
(define (AND lista)
  (cond ((null? lista) '())
  (else
(let recur ((uno lista) (dos '()))
           (cond
             ((and (car uno) (empty? (cdr uno))) (reverse dos))
             ((and (= (car uno) 1) (= (car (cdr uno)) 1)) (recur (cdr uno) (cons 1 dos)))
             ((or (= (car uno) 0) (= (car (cdr uno)) 0)) (recur (cdr uno) (cons 0 dos)))
         
           )
          )
         )
    )
  )

;;(OR LISTA)
;;Revisa si el valor de verdad al comparar con un OR los elementos de la lista
;;Devuelve una lista con 1 y 0 dependiendo del valor de verdad
(define (OR lista)
    (cond ((null? lista) '())
  (else
(let recur ((uno lista) (dos '()))
           (cond
             ((and (car uno) (empty? (cdr uno))) (reverse dos))
             ((or (= (car uno) 1) (= (car (cdr uno)) 1)) (recur (cdr uno) (cons 1 dos)))
             ((and (= (car uno) 0) (= (car (cdr uno)) 0)) (recur (cdr uno) (cons 0 dos)))
           )
          )
         )
    )
  )

;;(XOR LISTA)
;;Revisa si el valor de verdad al comparar con un XOR los elementos de la lista
;;Devuelve una lista con 1 y 0 dependiendo del valor de verdad
(define (XOR lista)
    (cond ((null? lista) '())
  (else
(let recur ((uno lista) (dos '()))
           (cond
             ((and (car uno) (empty? (cdr uno))) (reverse dos))
             ((and (= (car uno) 1) (= (car (cdr uno)) 0)) (recur (cdr uno) (cons 1 dos)))
             ((and (= (car uno) 0) (= (car (cdr uno)) 1)) (recur (cdr uno) (cons 1 dos)))
             (else (recur (cdr uno) (cons 0 dos)))
           )
          )
         )
    )
  )
(define oruga (lambda (x)
                (cond
                  ((eqv? (car x) 'A) (AND (car (cdr (car (cdr x))))))
                  ((eqv? (car x) 'O) (OR (car (cdr (car (cdr x))))))
                  ((eqv? (car x) 'X) (XOR (car (cdr (car (cdr x))))))
                  )
                
                )
  )
