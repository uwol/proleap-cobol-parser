 IDENTIFICATION DIVISION.
 PROGRAM-ID. TERMINATESTMT.
 PROCEDURE DIVISION.
    SEND SOMERECORD1 
       FROM SOMEID1
       WITH SOMEID2
       REPLACING LINE
       BEFORE ADVANCING 3 LINES.
       