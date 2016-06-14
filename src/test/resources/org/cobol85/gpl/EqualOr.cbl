000100 Identification Division.
000200 Program-ID. 
000300  IFEQUALORTEST.
000400 Procedure Division.
000500   IF FIELDA = (10 OR 15 OR 20 OR 25)
000600     Display "Hello world"
000700   END-IF.