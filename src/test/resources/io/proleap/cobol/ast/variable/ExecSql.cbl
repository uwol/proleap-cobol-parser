       Identification Division.
       Program-ID. ExecSql.
       
       Data Division.
       Working-Storage Section.
       
       EXEC SQL BEGIN DECLARE SECTION END-EXEC.
       
       01 userid pic x(8).
       
       EXEC SQL END DECLARE SECTION END-EXEC.
       
       Procedure Division.
        EXEC SQL CONNECT TO demo END-EXEC
       
        Display "Hello world".
        STOP RUN.