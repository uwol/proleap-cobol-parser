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


Build process:

* The build process is based on Maven.
* The test suite executes tests against NIST, CICS and some GPLed Cobol test files.
  * Due to license reasons, NIST and CICS Cobol test files are not included in this repo. 
  * However, the NIST test files can be found in the [Koopa repo](https://github.com/goblindegook/Koopa/tree/master/testsuite/cobol85).
  * After downloading those NIST test files, they have to be made available to the test suite via a system property: -DtestDirectory=/some/directory/
  

Release process:

* Milestones are published in the [ANTLR grammars repo](https://github.com/antlr/grammars-v4).