       IDENTIFICATION DIVISION.
       PROGRAM-ID. ExecCics.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.

         01 ws-length PICTURE 9(4).
       
         01 ws-input.
           05 ws-message-in PICTURE x(100).
       
         01 ws-output.
           05 ws-message-out PICTURE x(100).
       
       
       PROCEDURE DIVISION.
       
         MOVE 50 TO ws-length.
       
         EXEC CICS RECEIVE 
                   INTO(ws-input) 
                   LENGTH(ws-length)
         END-EXEC.

         MOVE ws-message-in TO ws-message-out.

         EXEC CICS SEND
                   FROM(ws-output)
                   LENGTH(ws-length)
         END-EXEC.
        
         EXEC CICS RETURN END-EXEC.
         
         Exit.