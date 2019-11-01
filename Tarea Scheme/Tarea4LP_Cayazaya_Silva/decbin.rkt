#lang racket

(define (decbin n)
  (let recursion ((final n))
   (cond
   ((< final 2) (number->string final))
   (else
    (string-append (recursion (quotient final 2)) (number->string (remainder final 2)))))))

(decbin 432)