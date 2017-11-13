       Identification Division.
       Program-ID. ExecSqlBeforeParagraph.
       Procedure Division.
           MOVE SPACE TO W-A.

           EXEC SQL
                WHENEVER SQLERROR CONTINUE
           END-EXEC.
      * 
       END-OF-JOBS.