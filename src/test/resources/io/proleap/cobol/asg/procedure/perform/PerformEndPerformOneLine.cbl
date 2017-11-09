       IDENTIFICATION DIVISION.
       PROGRAM-ID.    ISSUE09.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01  C-ZAEHLER                             PACKED-DECIMAL.
           05  C-S1                PIC S9(09)    VALUE +0.
           05  Z-T-MAX             pic 99 value 15.
        01  X-SUBSKRIPTE                        BINARY.
           05 X-1                  PIC S999.
           05 X-2                  PIC S999.
           05 X-3                  PIC S999.
       PROCEDURE DIVISION .
           PERFORM VARYING X-3 FROM 1 BY 1  UNTIL X-3  > Z-T-MAX  
      *       CONTINUE
           END-PERFORM

           display "X-3 nach Schleife=" x-3 
     
           exit.
