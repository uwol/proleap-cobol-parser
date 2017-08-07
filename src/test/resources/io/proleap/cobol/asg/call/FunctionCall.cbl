       IDENTIFICATION DIVISION.
       PROGRAM-ID. FUNCTIONCALL.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01  S-SCHALTER.
           05  S-ON1               PIC S9(01)      VALUE +1.
       01  V0P190.
           05  P190-PROG           PIC X(11).
           05  FILLER REDEFINES P190-PROG.
               10 P190-ENDE        PIC XX.
               10 FILLER           PIC X(09).
           05  FILLER REDEFINES P190-PROG.
               10 P190-PGM         PIC X(08).
               10 FILLER           PIC X(03).
           05  P190-TEXT           PIC X(120).
           05  FILLER REDEFINES P190-TEXT.
               10 P190-ID          PIC X(15).
               10 P190-IDT         PIC X(105).
           05  FILLER REDEFINES P190-TEXT.
               10 P190-Z1          PIC Z(8)9B.
               10 P190-TXT1        PIC X(30).
               10 P190-Z2          PIC Z(8)9B.
               10 P190-TXT2        PIC X(30).
               10 P190-Z3          PIC Z(8)9B.
               10 P190-TXT3        PIC X(30).
           05  FILLER REDEFINES P190-TEXT.
               10 P190-Z           PIC Z(8)9B      OCCURS 12.
           05  FILLER REDEFINES P190-TEXT.
               10 P190-T           PIC X(10)       OCCURS 12.
           05  FILLER REDEFINES P190-TEXT.
               10 P190-Z2N         PIC Z(5)9.99B   OCCURS 12.
           05  FILLER REDEFINES P190-TEXT.
               10 P190-Z3N         PIC Z(4)9.999B  OCCURS 12.
           05  P190-KENN           PIC X.
       PROCEDURE DIVISION.
           MOVE 'Y2600118 -'       TO P190-PROG.
           MOVE 'Version 003'      TO P190-TEXT.
           MOVE 'Compile-Datum: '      TO P190-TEXT (30:15).
           MOVE FUNCTION WHEN-COMPILED (7:2)
                                       TO P190-TEXT (45:02).
           MOVE '.'                    TO P190-TEXT (47:01).
           MOVE FUNCTION WHEN-COMPILED (5:2)
                                       TO P190-TEXT (48:02).
           MOVE '.'                    TO P190-TEXT (50:01).
           MOVE FUNCTION WHEN-COMPILED (1:4)
                                       TO P190-TEXT (51:04).
           MOVE FUNCTION WHEN-COMPILED (9:2)
                                       TO P190-TEXT (57:02).
           MOVE '.'                    TO P190-TEXT (59:01).
           MOVE FUNCTION WHEN-COMPILED (11:2)
                                       TO P190-TEXT (60:02).
           MOVE '.'                    TO P190-TEXT (62:01).
           MOVE FUNCTION WHEN-COMPILED (13:2)
                                       TO P190-TEXT (63:02).
           MOVE '0'                TO S-ON1.