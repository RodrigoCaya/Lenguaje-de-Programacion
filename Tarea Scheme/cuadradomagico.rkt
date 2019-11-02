#lang racket

;;(A lista) (B lista) (C lista)
;;A, B Y C son la posicion 1,2,3 de la primera fila respectivamente
;;Entrega el elemento retirado de la lista
(define (A lista) (car(car lista)))
(define (B lista) (car(cdr(car lista))))
(define (C lista) (car(cdr(cdr(car lista)))))
;;(D lista) (E lista) (F lista)
;;D, E Y F son la posicion 1,2,3 de la segunda fila respectivamente
;;Entrega el elemento retirado de la lista
(define (D lista) (car(car(cdr lista))))
(define (E lista) (car(cdr(car(cdr lista)))))
(define (F lista) (car(cdr(cdr(car(cdr lista))))))
;;(G lista) (H lista) (I lista)
;;G, H Y I son la posicion 1,2,3 de la tercera fila respectivamente
;;Entrega el elemento retirado de la lista
(define (G lista) (car(car(cdr(cdr lista)))))
(define (H lista) (car(cdr(car(cdr(cdr lista))))))
(define (I lista) (car(cdr(cdr(car(cdr(cdr lista)))))))

(define (magia matriz)
  (let recursion ((A (A matriz)) (B (B matriz)) (C (C matriz)) (D (D matriz)) (E (E matriz)) (F (F matriz)) (G (G matriz)) (H (H matriz)) (I (I matriz)))
    (cond
       ((and (not (eqv? A -1)) (eqv? I -1)) (recursion A B C D E F G H (- 10 A)))
        ((and (not (eqv? B -1)) (eqv? H -1)) (recursion A B C D E F G (- 10 B) I))
        ((and (not (eqv? C -1)) (eqv? G -1)) (recursion A B C D E F (- 10 C) H I))
        ((and (not (eqv? D -1)) (eqv? F -1)) (recursion A B C D E (- 10 D) G H I))
        ((and (not (eqv? F -1)) (eqv? D -1)) (recursion A B C (- 10 F) E F G H I))
        ((and (not (eqv? G -1)) (eqv? C -1)) (recursion A B (- 10 G) D E F G H I))
        ((and (not (eqv? H -1)) (eqv? B -1)) (recursion A (- 10 H) C D E F G H I))
        ((and (not (eqv? I -1)) (eqv? A -1)) (recursion (- 10 I) B C D E F G H I))
        ((eqv? E -1) (recursion A B C D 5 F G H I))
        (else
         (cond
           ((and (eqv? A -1) (not (eqv? B -1)) (not (eqv? C -1))) (recursion (- 15 (+ B C)) B C D E F G H I))
           ((and (eqv? B -1) (not (eqv? A -1)) (not (eqv? C -1))) (recursion A (- 15 (+ A C)) C D E F G H I))
           ((and (eqv? C -1) (not (eqv? A -1)) (not (eqv? G -1))) (recursion A B (- 15 (+ A B)) D E F G H I))
           ((and (eqv? D -1) (not (eqv? A -1)) (not (eqv? G -1))) (recursion A B C (- 15 (+ A G)) E F G H I))
           ((and (eqv? F -1) (not (eqv? C -1)) (not (eqv? I -1))) (recursion A B C D E (- 15 (+ C I)) G H I))
           ((and (eqv? G -1) (not (eqv? A -1)) (not (eqv? D -1))) (recursion A B C D E F (- 15 (+ A D)) H I))
           ((and (eqv? H -1) (not (eqv? G -1)) (not (eqv? I -1))) (recursion A B C D E F G (- 15 (+ G I)) I))
           ((and (eqv? I -1) (not (eqv? C -1)) (not (eqv? F -1))) (recursion A B C D E F G H (- 15 (+ C F))))
           ((and (not (eqv? I -1)) (not (eqv? B -1)) (eqv? D -1)) (recursion A B C (- (* I 2) B) E F G H I))

           ((and (eqv? B 9) (not (eqv? D 3)) (not (eqv? F 7)) (not (eqv? H 1))) (recursion A B C 3 E 7 G H I))
           ((and (eqv? B 3) (not (eqv? D 9)) (not (eqv? F 1)) (not (eqv? H 7))) (recursion A B C 3 E 7 G H I))
           ((and (eqv? B 1) (not (eqv? D 3)) (not (eqv? F 7)) (not (eqv? H 9))) (recursion A B C 3 E 7 G H I))
           ((and (eqv? B 7) (not (eqv? D 1)) (not (eqv? F 9)) (not (eqv? H 3))) (recursion A B C 3 E 7 G H I))

           ((and (eqv? H 9) (not (eqv? D 3)) (not (eqv? F 7)) (not (eqv? B 1))) (recursion A B C 3 E 7 G H I))
           ((and (eqv? H 3) (not (eqv? D 9)) (not (eqv? F 1)) (not (eqv? B 7))) (recursion A B C 3 E 7 G H I))
           ((and (eqv? H 1) (not (eqv? D 3)) (not (eqv? F 7)) (not (eqv? B 9))) (recursion A B C 3 E 7 G H I))
           ((and (eqv? H 7) (not (eqv? D 1)) (not (eqv? F 9)) (not (eqv? B 3))) (recursion A B C 3 E 7 G H I))

           ((and (eqv? B 1) (not (eqv? G 4))) (recursion A B C D E F 4 H I))
           ((and (eqv? B 3) (not (eqv? G 6))) (recursion A B C D E F 6 H I))
           ((and (eqv? B 7) (not (eqv? G 8))) (recursion A B C D E F 8 H I))
           ((and (eqv? B 9) (not (eqv? G 8))) (recursion A B C D E F 8 H I))

           ((and (eqv? D 9) (not (eqv? B 3)) (not (eqv? H 7)) (not (eqv? F 1))) (recursion A 3 C D E F G 7 I))
           ((and (eqv? D 3) (not (eqv? B 9)) (not (eqv? H 1)) (not (eqv? F 7))) (recursion A 3 C D E F G 7 I))
           ((and (eqv? D 1) (not (eqv? B 3)) (not (eqv? H 7)) (not (eqv? F 9))) (recursion A 3 C D E F G 7 I))
           ((and (eqv? D 7) (not (eqv? B 1)) (not (eqv? H 9)) (not (eqv? F 3))) (recursion A 3 C D E F G 7 I))

           ((and (eqv? F 9) (not (eqv? B 3)) (not (eqv? H 7)) (not (eqv? D 1))) (recursion A 3 C D E F G 7 I))
           ((and (eqv? F 3) (not (eqv? B 9)) (not (eqv? H 1)) (not (eqv? D 7))) (recursion A 3 C D E F G 7 I))
           ((and (eqv? F 1) (not (eqv? B 3)) (not (eqv? H 7)) (not (eqv? D 9))) (recursion A 3 C D E F G 7 I))
           ((and (eqv? F 7) (not (eqv? B 1)) (not (eqv? H 9)) (not (eqv? D 3))) (recursion A 3 C D E F G 7 I))

           ((and (eqv? D 1) (not (eqv? I 4))) (recursion A B C D E F G H 4))
           ((and (eqv? D 3) (not (eqv? I 6))) (recursion A B C D E F G H 6))
           ((and (eqv? D 7) (not (eqv? I 8))) (recursion A B C D E F G H 8))
           ((and (eqv? D 9) (not (eqv? I 8))) (recursion A B C D E F G H 8))
           
           ((and (eqv? A 8) (eqv? I 2) (eqv? C -1) (eqv? G -1)) (recursion A B 4 D E F 6 H I))
           ((and (eqv? A 2) (eqv? I 8) (eqv? C -1) (eqv? G -1)) (recursion A B 4 D E F 6 H I))
           ((and (eqv? A 6) (eqv? I 4) (eqv? C -1) (eqv? G -1)) (recursion A B 8 D E F 2 H I))
           ((and (eqv? A 4) (eqv? I 6) (eqv? C -1) (eqv? G -1)) (recursion A B 8 D E F 2 H I))
           
           ((and (eqv? C 8) (eqv? G 2) (eqv? A -1) (eqv? I -1)) (recursion 4 B C D E F G H 6))
           ((and (eqv? C 2) (eqv? G 8) (eqv? A -1) (eqv? I -1)) (recursion 4 B C D E F G H 6))
           ((and (eqv? C 6) (eqv? G 4) (eqv? A -1) (eqv? I -1)) (recursion 8 B C D E F G H 2))
           ((and (eqv? C 4) (eqv? G 6) (eqv? A -1) (eqv? I -1)) (recursion 8 B C D E F G H 2))
           
           (else
            (list (list A B C) (list D E F) (list G H I)))
           )
        
        )

         )
    )
  )

(magia '((-1 -1 -1) (3 5 7) (-1 -1 -1)))