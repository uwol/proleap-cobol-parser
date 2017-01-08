ANTLR4-based parser and grammar for Cobol 85
============================================

[![Build](https://img.shields.io/travis/uwol/cobol85parser.svg)](https://travis-ci.org/uwol/cobol85parser)
[![Coverage](https://coveralls.io/repos/github/uwol/cobol85parser/badge.svg?branch=master)](https://coveralls.io/github/uwol/cobol85parser?branch=master)
[![License](https://img.shields.io/badge/License-BSD%203--Clause-blue.svg)](https://opensource.org/licenses/BSD-3-Clause)

This is a parser and grammar for Cobol 85, which generates an
Abstract Syntax Tree (AST) and Abstract Semantic Graph (ASG) for COBOL 85 code.
The AST represents plain COBOL source code in a syntax tree structure.
The ASG is generated from the AST by semantic analysis and provides data and control
flow information (e. g. variable access).


Example
-------

### Input: COBOL 85 code

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
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```
<dependency>
    <groupId>com.github.uwol</groupId>
    <artifactId>cobol85parser</artifactId>
    <version>-SNAPSHOT</version>
</dependency>
```

Use the following code as a starting point for developing own code.

### Simple: Generate an Abstract Semantic Graph (ASG) from COBOL code

```java
io.proleap.cobol.asg.applicationcontext.CobolParserContextFactory.configureDefaultApplicationContext();

// generate ASG from plain COBOL code
java.io.File inputFile = new java.io.File("src/test/resources/io/proleap/cobol/asg/HelloWorld.cbl");
io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum format = io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum.TANDEM;
io.proleap.cobol.asg.metamodel.Program program = io.proleap.cobol.asg.applicationcontext.CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, format);

// navigate on ASG
io.proleap.cobol.asg.metamodel.CompilationUnit compilationUnit = program.getCompilationUnit("HelloWorld");
io.proleap.cobol.asg.metamodel.ProgramUnit programUnit = compilationUnit.getProgramUnit();
io.proleap.cobol.asg.metamodel.data.DataDivision dataDivision = programUnit.getDataDivision();
io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry dataDescriptionEntry = dataDivision.getWorkingStorageSection().getDataDescriptionEntry("ITEMS");
Integer levelNumber = dataDescriptionEntry.getLevelNumber();
```

### Complex: Generate an Abstract Semantic Graph (ASG) and traverse the Abstract Syntax Tree (AST)

```java
io.proleap.cobol.asg.applicationcontext.CobolParserContextFactory.configureDefaultApplicationContext();

// generate ASG from plain COBOL code
java.io.File inputFile = new java.io.File("src/test/resources/io/proleap/cobol/asg/HelloWorld.cbl");
io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum format = io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum.TANDEM;
io.proleap.cobol.asg.metamodel.Program program = io.proleap.cobol.asg.applicationcontext.CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, format);

// traverse the AST
io.proleap.cobol.Cobol85BaseVisitor<Boolean> visitor = new io.proleap.cobol.Cobol85BaseVisitor<Boolean>() {
  @Override
  public Boolean visitDataDescriptionEntryFormat1(final io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context ctx) {
    io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry entry = (io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry) io.proleap.cobol.asg.applicationcontext.CobolParserContext.getInstance().getASGElementRegistry().getASGElement(ctx);
    String name = entry.getName();

    return visitChildren(ctx);
  }
};

for (final io.proleap.cobol.asg.metamodel.CompilationUnit compilationUnit : program.getCompilationUnits()) {
  visitor.visit(compilationUnit.getCtx());
}
```


Characteristics
---------------

1. The parser passes the NIST test suite.

2. To be used in conjunction with the provided preprocessor, which executes
   COPY and REPLACE statements.

3. The ANTLR4 grammar is akin but neither copied from nor identical to the Cobol.jj, Cobol.kg and VS COBOL II grammars.

4. For parsing large Cobol source code files, following VM args have to be set: `-Xmx2048m -XX:MaxPermSize=256m`.
   Intellij Plugin for ANTLR 4 has to be provided with those VM args in file `idea.vmoptions`.


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

Tests run: 622, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

* To install the JAR in your local Maven repository:

```
$ mvn clean install
```

* To use the JAR in your Maven project, add following dependency to the pom.xml of your project:

```
<dependency>
  <groupId>io.github.uwol</groupId>
  <artifactId>cobol85parser</artifactId>
  <version>2.0.0</version>
</dependency>
```

* To only run the tests:

```
$ mvn clean test
```


Release process
---------------

* Milestones are published in the [ANTLR grammars repo](https://github.com/antlr/grammars-v4).


License
-------

Licensed under the BSD 3-Clause License. See LICENSE for details.

### And finally...

Patches accepted, or create an issue!
I'd love, if you could send me a note in which context you're using the parser. Thank you!
