#lang racket
(define (div l)
    (cond ((null? l) '())
        (else
         (let recur ((x (list(car l))) (y (cdr l)))
           (cond
             ((eqv? y '())
              (list )
              )
            (else
           (cond
             ((eqv? (sum x) (sum y))
              (list x y)
              )
             (else
              (cond ((eqv? (cdr x) '()) 
                     (recur (cons (car x) (cons (car y) '()))(cdr y))
                     )
                    (else
                     (recur (cons (car x) (cons (car (cdr x)) (cons (car y) '())))(cdr y))
                     )
               )
              )
           )
          )
            )
           )
         )
    )
  )

;;(sum elem)
;;suma los valores de la lista elem
;;entrega la suma
(define (sum elem)
  (cond
    ((eqv? (length elem) 1)
     (car elem)
     )
    (else
     (+ (car elem)(sum (cdr elem)))
     )
   )
)

(div '(4 5 6 7 8))