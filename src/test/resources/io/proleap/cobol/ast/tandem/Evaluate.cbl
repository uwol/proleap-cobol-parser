 Identification Division.
 Program-ID. EVALSTMT.
 Procedure Division.
     EVALUATE     SOMEVAR1
             ALSO 123
             ALSO (SOMEVAR2 * 9)
             ALSO IT-IS-81
             ALSO TRUE
             ALSO FALSE
        WHEN NOT  SOMEVAR3
             ALSO SOMEVAR4
             ALSO 234
             ALSO TRUE
             ALSO SOMEVAR5 = 0
             ALSO SOMEVAR6 < 9
                  MOVE  "A" TO SOMEVAR1
                  MOVE  "B" TO SOMEVAR2
        WHEN      42
             ALSO SOMEVAR1
             ALSO (9 * 9 * 9)
             ALSO FALSE
             ALSO SOMEVAR1-2 = "*"
             ALSO SOMEVAR > 8
                  MOVE  "C" TO SOMEVAR1
                  MOVE  "D" TO SOMEVAR2
        WHEN      ANY
             ALSO ANY
             ALSO ANY
             ALSO ANY
             ALSO ANY
             ALSO SOMEVAR = 23
                  MOVE  "E" TO SOMEVAR1
                  MOVE  "F" TO SOMEVAR2
        WHEN      OTHER
                  MOVE  "G" TO SOMEVAR1
                  MOVE  "H" TO SOMEVAR2
     END-EVALUATE.                                                