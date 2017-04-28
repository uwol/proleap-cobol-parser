000100 IDENTIFICATION DIVISION.                                         12345678
000100 PROGRAM-ID. LINECONT.                                            12345678
000100 DATA DIVISION                                                    12345678
000100 77   SQL-INS            PIC X(150) VALUE                         12345678
000100         "INSERT INTO EMP (EMPNO,ENAME,JOB,SAL,DEPTNO)            12345678
000100-        " VALUES (:EMPNO,:ENAME,:JOB,:SAL,:DEPTNO)".             12345678