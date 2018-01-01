ProLeap ANTLR4-based parser for COBOL
=====================================

This is a **COBOL parser** based on an [ANTLR4 grammar](src/main/antlr4/io/proleap/cobol/Cobol85.g4), 
which generates an **Abstract Syntax Tree** (AST) and **Abstract Semantic Graph** (ASG) for COBOL code.
The AST represents plain COBOL source code in a syntax tree structure.
The ASG is generated from the AST by **semantic analysis** and provides data and control
flow information (e. g. variable access). EXEC SQL, EXEC SQLIMS and EXEC CICS
statements are extracted as texts.

The parser is developed test-driven, passes the **NIST test suite** and has successfully been **applied to numerous COBOL files** from banking and insurance.

💫 **Star** if you like our work.

[![Build](https://img.shields.io/travis/uwol/cobol85parser.svg)](https://travis-ci.org/uwol/cobol85parser)
[![Coverage](https://coveralls.io/repos/github/uwol/cobol85parser/badge.svg?branch=master)](https://coveralls.io/github/uwol/cobol85parser?branch=master)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![ProLeap on Twitter](https://img.shields.io/twitter/follow/proleap_io.svg?style=social&label=Follow)](https://twitter.com/proleap_io)


Example
-------

### Input: COBOL code

```
 Identification Division.
 Program-ID.
  HELLOWORLD.
 Procedure Division.
  Display "Hello world".
  STOP RUN.
```


### Output: Abstract Syntax Tree (AST)

```
(startRule
	(compilationUnit
		(programUnit
			(identificationDivision Identification Division .
				(programIdParagraph Program-ID .
					(programName
						(cobolWord HELLOWORLD)) .))
			(procedureDivision Procedure Division .
				(procedureDivisionBody
					(paragraphs
						(sentence
							(statement
								(displayStatement Display
									(displayOperand
										(literal "Hello world")))) .)
						(sentence
							(statement
								(stopStatement STOP RUN))) .)))))) <EOF>)
```


Getting started
---------------

To include the parser in your Maven project edit your `pom.xml` file as follows

```
<repositories>
	<repository>
		<id>maven.proleap.io</id>
		<url>http://maven.proleap.io</url>
	</repository>
</repositories>
```

```
<dependency>
	<groupId>io.github.uwol</groupId>
	<artifactId>cobol85parser</artifactId>
	<version>2.4.0</version>
</dependency>
```

Use the following code as a starting point for developing own code.

### Simple: Generate an Abstract Semantic Graph (ASG) from COBOL code

```java
// generate ASG from plain COBOL code
java.io.File inputFile = new java.io.File("src/test/resources/io/proleap/cobol/asg/HelloWorld.cbl");
io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum format = io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum.TANDEM;
io.proleap.cobol.asg.metamodel.Program program = new io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl().analyzeFile(inputFile, format);

// navigate on ASG
io.proleap.cobol.asg.metamodel.CompilationUnit compilationUnit = program.getCompilationUnit("HelloWorld");
io.proleap.cobol.asg.metamodel.ProgramUnit programUnit = compilationUnit.getProgramUnit();
io.proleap.cobol.asg.metamodel.data.DataDivision dataDivision = programUnit.getDataDivision();
io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry dataDescriptionEntry = dataDivision.getWorkingStorageSection().getDataDescriptionEntry("ITEMS");
Integer levelNumber = dataDescriptionEntry.getLevelNumber();
```

### Complex: Generate an Abstract Semantic Graph (ASG) and traverse the Abstract Syntax Tree (AST)

```java
// generate ASG from plain COBOL code
java.io.File inputFile = new java.io.File("src/test/resources/io/proleap/cobol/asg/HelloWorld.cbl");
io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum format = io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum.TANDEM;
io.proleap.cobol.asg.metamodel.Program program = new io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl().analyzeFile(inputFile, format);

// traverse the AST
io.proleap.cobol.Cobol85BaseVisitor<Boolean> visitor = new io.proleap.cobol.Cobol85BaseVisitor<Boolean>() {
  @Override
  public Boolean visitDataDescriptionEntryFormat1(final io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context ctx) {
    io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry entry = (io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry) program.getASGElementRegistry().getASGElement(ctx);
    String name = entry.getName();

    return visitChildren(ctx);
  }
};

for (final io.proleap.cobol.asg.metamodel.CompilationUnit compilationUnit : program.getCompilationUnits()) {
  visitor.visit(compilationUnit.getCtx());
}
```


Where to look next
------------------

- [ANTLR4 COBOL grammar](src/main/antlr4/io/proleap/cobol/Cobol85.g4)
- [ANTLR4 COBOL preprocessor grammar](src/main/antlr4/io/proleap/cobol/Cobol85Preprocessor.g4)
- [Unit test code examples](src/test/java/io/proleap/cobol/asg/data/workingstorage)


Features
--------

* `EXEC SQL` statements, `EXEC SQLIMS` statements and `EXEC CICS` statements are extracted by the preprocessor and provided as texts in the ASG.
* Passes the [NIST test suite](http://www.itl.nist.gov/div897/ctg/cobol_form.htm).
* Rigorous test-driven development.
* To be used in conjunction with the provided preprocessor, which executes `COPY`, `REPLACE`, `CBL` and `PROCESS` statements.


Build process
-------------

The build process is based on Maven (version 3 or higher). Building requires a JDK 8 and generates a Maven JAR, which can be used in other Maven projects as a dependency.

* Clone or download the repository.
* In [Eclipse](https://eclipse.org) import the directory as a an `existing Maven project`.
* To build, run:

```
$ mvn clean package
```

* The test suite executes AST and ASG tests against COBOL test code and NIST test files. NIST test files come from [Koopa repo](https://github.com/goblindegook/Koopa/tree/master/testsuite/cobol85). Unit tests and parse tree files were generated by class `io.proleap.cobol.TestGenerator` from COBOL test files. The generator derives the COBOL line format from the containing folder name.
* You should see output like this:

```
[INFO] Scanning for projects...
...
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running io.proleap.cobol.ast.fixed.FixedTest
Preprocessing file Fixed.cbl.
Parsing file Fixed.cbl.
Comparing parse tree with file Fixed.cbl.tree.
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 6.202 sec
Running io.proleap.cobol.ast.fixed.QuotesInCommentEntryTest
...
Results :

Tests run: 680, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

* To install the JAR in your local Maven repository:

```
$ mvn clean install
```

* To only run the tests:

```
$ mvn clean test
```


Release process
---------------

* Releases and snapshots are published in the MAVEN repository `maven.proleap.io`.
* Milestones of the grammar are published in the [ANTLR grammars repo](https://github.com/antlr/grammars-v4).


License
-------

Licensed under the MIT License. See LICENSE for details.
