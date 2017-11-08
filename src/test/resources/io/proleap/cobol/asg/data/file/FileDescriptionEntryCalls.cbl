       IDENTIFICATION DIVISION.
       PROGRAM-ID. ISSUE14.
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.       
           SELECT D111E            ASSIGN TO UT-S-D111E.       
       DATA DIVISION.
       FILE SECTION.
       FD  D111E
           LABEL RECORDS STANDARD
           RECORDING F
           BLOCK 0 RECORDS
           DATA RECORD D111E-DATEI.
       01  D111E-DATEI.
           05 D111E-PROJ           PIC 9(02).
           05 D111E-DST            PIC 9(03).
           05 D111E-BEA            PIC X(03).
           05 D111E-PERSNR         PIC 9(09).
           05 D111E-BDST           PIC 9(08).
           05 D111E-NAME           PIC X(30).
           05 D111E-VORNAME        PIC X(30).
           05 D111E-TITEL          PIC X(20).
           05 D111E-VORSATZ        PIC X(20).
           05 D111E-ADEL           PIC X(20).
           05 D111E-GESCHLECHT     PIC X(01).
           05 D111E-HAUSNR         PIC X(08).
           05 D111E-KNR            PIC 9(02).
           05 D111E-KNAME          PIC X(16).
           05 D111E-DAT-M          PIC 9(02).
           05 D111E-DAT-J          PIC 9(04).
           05 D111E-GEB-DAT.
              10 D111E-GEB-DAT-T   PIC 9(02).
              10 D111E-GEB-DAT-M   PIC 9(02).
              10 D111E-GEB-DAT-J   PIC 9(04).
           05 D111E-PLZ            PIC X(10).
           05 D111E-ORT            PIC X(34).
           05 D111E-STRASSE        PIC X(33).
           05 D111E-P7142          PIC X(40).
           05 D111E-LAND           PIC X(30).
           05 D111E-SATZART        PIC X(01).
           05 D111E-ANSCHRIFT      PIC X(01).
           05 D111E-KNACHNAME      PIC X(30).
           05 D111E-REST           PIC X(15).
       WORKING-STORAGE SECTION.
       01  WS-A            PIC X(22) VALUE '**WS-SECTION DY21276**'.       
       01  AUSGABE.
           05 AUS-VOR              PIC X(01)       VALUE SPACE.
           05 AUS-TEXT             PIC X(132)      VALUE SPACE.
           05 AUS-FONT-INDEX       PIC X(01)       VALUE '1'.
       PROCEDURE DIVISION.
           OPEN INPUT D111E
           READ D111E
              AT END
                 move '1' to aus-vor
                 MOVE 'Ende' to aus-text
              NOT AT END
                 Move "weiter lesen" to aus-text
           END-READ
           display ausgabe
           .
