ANTLR4-based grammar and parser for Cobol 85
============================================

<a href="https://travis-ci.org/uwol/cobol85grammar"><img src="https://api.travis-ci.org/uwol/cobol85grammar.png"></a>

This is an approximate grammar and parser for Cobol 85, which generates an 
Abstract Syntax Tree (AST) and Abstract Semantic Graph (ASG) for COBOL 85 code.

The AST represents plain COBOL source code in a syntax tree structure. 
The ASG is generated from the AST by a semantic analysis and provides data and control 
flow information (e. g. variable access).

The grammar is akin but neither copied from nor identical to the Cobol.jj, 
Cobol.kg and VS COBOL II grammars.


Characteristics
---------------

1. Passes NIST test suite.

2. To be used in conjunction with the provided preprocessor, which executes
   COPY and REPLACE statements.


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

### Output: abstract syntax tree (AST)

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
							(statements
								(statement
									(displayStatement Display
										(literal "Hello world")))) .)
						(sentence
							(statements
								(statement
									(stopStatement STOP RUN))) .)))))) <EOF>)
```


Execution
---------

### Abstract Semantic Graph (ASG) parsing

```java
io.proleap.cobol.parser.applicationcontext.CobolParserContextFactory.configureDefaultApplicationContext();

java.io.File inputFile = new java.io.File("src/test/resources/io/proleap/cobol/gpl/parser/HelloWorld.cbl");

/*
* semantic analysis
*/
io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum format = io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum.TANDEM;
io.proleap.cobol.parser.metamodel.Program program = io.proleap.cobol.parser.applicationcontext.CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null, format);

io.proleap.cobol.parser.metamodel.CopyBook copyBook = program.getCopyBook("HelloWorld");
io.proleap.cobol.parser.metamodel.ProgramUnit programUnit = copyBook.getProgramUnit();
io.proleap.cobol.parser.metamodel.data.DataDivision dataDivision = programUnit.getDataDivision();
io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry dataDescriptionEntry = dataDivision.getDataDescriptionEntry("ITEMS");
```

### Abstract Syntax Tree (AST) parsing

```java
io.proleap.cobol.applicationcontext.CobolGrammarContextFactory.configureDefaultApplicationContext();

java.io.File inputFile = new java.io.File("src/test/resources/io/proleap/cobol/gpl/HelloWorld.cbl");
java.io.File libDirectory = inputFile.getParentFile();

/*
* preprocessor
*/
io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum format = io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum.TANDEM;
String preProcessedInput = io.proleap.cobol.applicationcontext.CobolGrammarContext.getInstance().getCobolPreprocessor().process(inputFile, libDirectory, null, format);

/*
* lexer
*/
org.antlr.v4.runtime.ANTLRInputStream antlrInputStream = new org.antlr.v4.runtime.ANTLRInputStream(preProcessedInput);
io.proleap.cobol.Cobol85Lexer lexer = new io.proleap.cobol.Cobol85Lexer(antlrInputStream);
org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);

/*
* parser
*/
io.proleap.cobol.Cobol85Parser parser = new io.proleap.cobol.Cobol85Parser(tokens);
io.proleap.cobol.Cobol85Parser.StartRuleContext ctx = parser.startRule();

/*
* traverse AST with ANTLR visitor
*/
io.proleap.cobol.Cobol85BaseVisitor<Boolean> visitor = new io.proleap.cobol.Cobol85BaseVisitor<Boolean>();
visitor.visit(ctx);
```

### Abstract Semantic Graph (ASG) parsing with Abstract Syntax Tree (AST) traversal

```java
io.proleap.cobol.parser.applicationcontext.CobolParserContextFactory.configureDefaultApplicationContext();

java.io.File inputFile = new java.io.File("src/test/resources/io/proleap/cobol/gpl/parser/HelloWorld.cbl");

/*
 * semantic analysis
 */
io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum format = io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum.TANDEM;
io.proleap.cobol.parser.metamodel.Program program = io.proleap.cobol.parser.applicationcontext.CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null, format);

/*
* traverse AST with ANTLR visitor
*/
io.proleap.cobol.Cobol85BaseVisitor<Boolean> visitor = new io.proleap.cobol.Cobol85BaseVisitor<Boolean>() {
  @Override
  public Boolean visitDataDescriptionEntryFormat1(final io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context ctx) {
    io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry entry = (io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry) io.proleap.cobol.parser.applicationcontext.CobolParserContext.getInstance().getASGElementRegistry().getASGElement(ctx);
    String name = entry.getName();

    return visitChildren(ctx);
  }
};

for (final io.proleap.cobol.parser.metamodel.CopyBook copyBook : program.getCopyBooks()) {
  visitor.visit(copyBook.getCtx());
}
```


VM args
-------

* For parsing large Cobol source code files,  VM args have to be set: -Xmx2048m -XX:MaxPermSize=256m
* Intellij Plugin for ANTLR 4 has to be provided with those VM args in file idea.vmoptions.


Build process
-------------

* The build process is based on Maven (version 3 or higher). Building requires a JDK 8.
* To build, run:

```
$ mvn clean package
```

* The test suite executes tests against NIST, CICS and some GPLed Cobol test files. NIST test files come from [Koopa repo](https://github.com/goblindegook/Koopa/tree/master/testsuite/cobol85). Unit tests and parse tree files were generated by class io.proleap.cobol.TestGenerator from COBOL test files. The generator derives the COBOL line format from the containing folder name (e.g. org/cobol85/gpl/fixed).
* To only run the tests:

```
$ mvn clean test
```

* You should see output like this:

```
[INFO] Scanning for projects...
...
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running io.proleap.cobol.gpl.fixed.FixedTest
Preprocessing file Fixed.cbl.
Parsing file Fixed.cbl.
Comparing parse tree with file Fixed.cbl.tree.
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 6.202 sec
Running io.proleap.cobol.gpl.fixed.QuotesInCommentEntryTest
...
Results :

Tests run: 504, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

* To install the jar in your local Maven repository:

```
$ mvn clean install
```

* To use it in your Maven project, add following dependency to your pom.xml:

```
<dependency>
  <groupId>io.github.uwol</groupId>
  <artifactId>cobol85grammar</artifactId>
  <version>1.5.0</version>
</dependency>
```


Release process
---------------

* Milestones are published in the [ANTLR grammars repo](https://github.com/antlr/grammars-v4).


License
-------

Licensed under the BSD 3-Clause License. See LICENSE for details.

### And finally...

Patches accepted, or create an issue!
I'd love, if you could send me a note in which context you're using the grammar. Thank you!
