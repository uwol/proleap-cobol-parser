       IDENTIFICATION DIVISION.
       PROGRAM-ID. SomeVideoProgram.
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           SELECT OPTIONAL VideoFile 
               ASSIGN TO 'IDXVIDEO'
               ORGANIZATION IS INDEXED
               ACCESS MODE IS DYNAMIC
               RECORD KEY IS VideoCode of VideoRecord
               ALTERNATE RECORD KEY IS VideoTitle of VideoRecord
                  WITH DUPLICATES
               FILE STATUS IS VideoFileStatus.
       DATA DIVISION.
       FILE SECTION.
       FD  VideoFile.
       01  VideoRecord.
           02 VideoCode               PIC 9(5).
           02 VideoTitle              PIC X(40).
       WORKING-STORAGE SECTION.
       01  VideoFileStatus              PIC X(2).