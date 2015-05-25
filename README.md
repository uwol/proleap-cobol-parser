cobol85grammar
==================================================

Cobol 85 Grammar for ANTLR4

This is an approximate grammar for Cobol 85. It is akin but neither 
copied from nor identical to Cobol.jj, Cobol.kg and VS COBOL II grammars.
Tested against the NIST test suite.

Characteristics:

1. Passes the NIST tests.

2. To be used in conjunction with the provided preprocessor, which executes 
   COPY and REPLACE statements.


Known limitations (work under progress):

1. Picture strings are parsed as (groups of) terminal symbols.

2. Comments are skipped.