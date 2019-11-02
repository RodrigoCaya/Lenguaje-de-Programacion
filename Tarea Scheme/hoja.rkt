#lang racket
(define (hoja arbol)
  (let auxlet ((l (aux arbol)))
    (aux2 l)
    )
)

;;(aux arvol)
;;recorre en preorden el arbol
;;entrega una lista de listas con (nodo,altura)
(define (aux arvol)
  (let recur ((nodo arvol) (contador 0))
    (cond ((null? nodo)'()
        )
        (else
         (append (list(list(car nodo)(+ 1 contador)))
                (recur (cadr nodo)(+ 1 contador))
                (recur (caddr nodo)(+ 1 contador))
                )
         )
      )
    )
 )

;;(aux2 l)
;;busca el nodo con mayor altura
;;retorna el(los) nodos con mayor altura
(define (aux2 l)
  (let rec ((aux l)(nod (list(car(car l))))(altura (car(cdr(car l))))(nodomax '())(alturamax 0))
    (cond
      ((eqv? (cdr aux) '())
       (cond
         ((> altura alturamax) nod)
         ((= altura alturamax) (append nod nodomax))
         (else
          nodomax
          )
         )
       )
      (else
       (cond
         ((> altura alturamax) (rec (cdr aux)(list(car(car(cdr aux))))(car(cdr(car(cdr aux)))) nod altura))
         ((= altura alturamax) (rec (cdr aux)(list(car(car(cdr aux))))(car(cdr(car(cdr aux)))) (append nod nodomax) altura))
         (else
          (rec (cdr aux)(list(car(car(cdr aux))))(car(cdr(car(cdr aux)))) nodomax alturamax)
          )
         )
       )
      )
    )
  )


(hoja '(1 ( 2 (4 () ()) (5 (8 () ()) ())) (3 (6 () (7 () ())) ())) )