#lang racket
(define (caja cajeros lista)
  (let recur ((caj cajeros)(aux (cdr lista))(contador 1)(l (list(list 1 1 (car lista))))(lfinal (list 1))(contador2 1)(contadorcl 1)(l2 (list(list 1 1 (car lista)))))
    (cond
      ((eqv? aux '()) lfinal)
    (else
    (cond
      ((eqv? caj contador)
       (recur caj aux(- contador 1) (dif l) lfinal contador2 (- (car(faux(dif l2))) 1)(remove (faux(dif l2)) (dif l2)))
       )
     (else
      (recur caj (cdr aux)(+ 1 contador)(append l (list(list(+ 1 contadorcl)(+ 1 contador2)(car aux))))(append lfinal (list (+ 1 contadorcl)))(+ 1 contador2)(+ 1 contadorcl)(append l2 (list(list(+ 1 contadorcl)(+ 1 contador2)(car aux)))))
      )
     )
    )
    )
  )
 )

;;(faux lista)
;;revisa el 3er valor de la lista, el cual equivale al tiempo que se demora el cliente
;;retorna la lista que tenga 0 en el tiempo
(define (faux lista)
  (let rec ((aux lista))
    (cond
      ((null? aux) 0)
      ) 
    (cond
      ((eqv? (car(cdr(cdr(car aux)))) 0)
       (car aux))
      (else
       (rec (cdr aux))
       )
      )
    )
  )
       
;;(dif lista)
;;busca el que tenga menor tiempo y se lo resta a cada uno de los clientes
;;retorna la lista actualizada
(define (dif lista)
  (let rec ((aux lista)(menor +inf.0))
    (cond
      ((null? aux)
       (let rec2 ((l lista)(resta menor)(l2 '()))
         (cond
           ((null? l) l2)
           (else
            (rec2 (cdr l) resta (append l2 (list(list(car(car l))(car(cdr(car l)))(-(car(cdr(cdr(car l)))) resta)))))
            )
           )
         )
       )
      (else
       (cond
         ((> menor (car(cdr(cdr(car aux)))))
          (rec (cdr aux)(car(cdr(cdr(car aux)))))
          )
         (else
          (rec (cdr aux) menor)
          )
       )
       )
      )
    )
  )


(caja 3 '(406 424 87 888 871 915 516 81 275 578))