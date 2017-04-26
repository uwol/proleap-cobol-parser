000100 IDENTIFICATION DIVISION.                                         12345678
000100 PROGRAM-ID. ExecCics.                                            12345678
000100                                                                  12345678
000100 DATA DIVISION.                                                   12345678
000100 WORKING-STORAGE SECTION.                                         12345678
000100                                                                  12345678
000100   01 ws-length PICTURE 9(4).                                     12345678
000100                                                                  12345678
000100   01 ws-input.                                                   12345678
000100     05 ws-message-in PICTURE x(100).                             12345678
000100                                                                  12345678
000100   01 ws-output.                                                  12345678
000100     05 ws-message-out PICTURE x(100).                            12345678
000100                                                                  12345678
000100                                                                  12345678
000100 PROCEDURE DIVISION.                                              12345678
000100                                                                  12345678
000100   MOVE 50 TO ws-length.                                          12345678
000100                                                                  12345678
000100   EXEC CICS RECEIVE                                              12345678
000100             INTO(ws-input)                                       12345678
000100             LENGTH(ws-length)                                    12345678
000100   END-EXEC.                                                      12345678
000100                                                                  12345678
000100   MOVE ws-message-in TO ws-message-out.                          12345678
000100                                                                  12345678
000100   EXEC CICS SEND                                                 12345678
000100             FROM(ws-output)                                      12345678
000100             LENGTH(ws-length)                                    12345678
000100   END-EXEC.                                                      12345678
000100                                                                  12345678
000100   EXEC CICS RETURN END-EXEC.                                     12345678