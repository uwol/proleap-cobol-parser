000100 Identification Division.                                         12345678
000100 Program-ID. ExecSqlIms.                                          12345678
000100 Procedure Division.                                              12345678
000100                                                                  12345678
000100  EXEC SQLIMS                                                     12345678
000100    DECLARE SOMECUR CURSOR FOR DYSQL                              12345678
000100  END-EXEC.                                                       12345678
000100                                                                  12345678
000100  EXEC SQLIMS                                                     12345678
000100    OPEN SOMECUR                                                  12345678
000100  END-EXEC.                                                       12345678
000100                                                                  12345678
000100  EXEC SQLIMS                                                     12345678
000100    FETCH SOMECUR INTO :SOMECOL1, :SOMECOL2                       12345678
000100  END-EXEC.                                                       12345678
000100                                                                  12345678
000100  IF SQLIMSCODE = 100                                             12345678
000100    PERFORM NO-DATA-FOUND                                         12345678
000100  ELSE                                                            12345678
000100    PERFORM LOAD-DATA UNTIL SQLIMSCODE NOT EQUAL TO ZERO.         12345678
000100                                                                  12345678
000100  EXEC SQLIMS                                                     12345678
000100    CLOSE SOMECUR                                                 12345678
000100  END-EXEC.                                                       12345678