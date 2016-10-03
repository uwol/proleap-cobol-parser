000000 Identification Division.
000000   Program-ID.
000000     IFELSEMOVETEST.
000000 Procedure Division.
000000
000000 IF Obj-Name > ZERO
000000   SET ADDRESS OF PointerA
000000       TO PointerB
000000 ELSE
000000   MOVE 'test' TO testvar
000000 END-IF.
 
   