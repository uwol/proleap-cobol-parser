000100 IDENTIFICATION DIVISION.                                         12345678
000100 PROGRAM-ID. HELLO.                                               12345678
000100                                                                  12345678
000100 DATA DIVISION.                                                   12345678
000100 WORKING-STORAGE SECTION.                                         12345678
000100   EXEC SQL                                                       12345678
000100     INCLUDE SQLSCRIPT                                            12345678
000100   END-EXEC.                                                      12345678
000100                                                                  12345678
000100   EXEC SQL                                                       12345678
000100     INCLUDE TEACHER                                              12345678
000100   END-EXEC.                                                      12345678
000100                                                                  12345678
000100   EXEC SQL BEGIN DECLARE SECTION                                 12345678
000100   END-EXEC.                                                      12345678
000100     01 WS-TEACHER-REC.                                           12345678
000100     05 WS-TEACHER-ID PIC 9(10).                                  12345678
000100   EXEC SQL END DECLARE SECTION                                   12345678
000100   END-EXEC.                                                      12345678
000100                                                                  12345678
000100 PROCEDURE DIVISION.                                              12345678
000100   EXEC SQL                                                       12345678
000100     SELECT TEACHER-ID                                            12345678
000100       INTO :WS-TEACHER-ID FROM TEACHER                           12345678
000100       WHERE TEACHER-ID=1                                         12345678
000100   END-EXEC.                                                      12345678
000100                                                                  12345678
000100   IF SQLCODE=0                                                   12345678
000100   DISPLAY WS-TEACHER-RECORD                                      12345678
000100   ELSE DISPLAY 'Error'                                           12345678
000100   END-IF.                                                        12345678
000100   STOP RUN.                                                      12345678