000100 Identification Division.
000200 Program-ID. 
000300  CPYSUBDIR.
000400 Procedure Division.
000500  COPY "somedir/CopyBook1.cpy".
000500  COPY "soMeDir/cOpYbOoK2.cpy".
000500  COPY "somedir\CopyBook3.cpy".
000500  COPY "./somedir/CopyBook4.cpy".
000500  COPY ".\somedir\CopyBook5.cpy".
000600  STOP RUN.