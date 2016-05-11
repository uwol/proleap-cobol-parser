package my.test;

import java.io.IOException;

import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Esempio2 {

	public static void main(String[] args) throws IOException {

		org.cobol85.applicationcontext.Cobol85GrammarContextFactory.configureDefaultApplicationContext();
		
		//20160501 AC
		//String fname="d:\\docs\\coboldocs\\TEST1.COB"; //"test/resources/org/cobol85/gpl/HelloWorldVar.cbl";
		String fname="src/test/resources/org/cobol85/gpl/Replace_bug.cbl";
		
		final java.io.File inputFile = new java.io.File(fname);
		final java.io.File libDirectory = inputFile.getParentFile();

		/*
		 * COBOL preprocessor
		 */
		// if automatic detection of COBOL line format fails, it has to be set
		// here
		final org.cobol85.preprocessor.Cobol85Preprocessor.Cobol85FormatEnum[] formats = null;
		final String preProcessedInput = org.cobol85.applicationcontext.Cobol85GrammarContext.getInstance()
				.getCobol85Preprocessor().process(inputFile, libDirectory, formats);

		//System.out.println(preProcessedInput);
		
		/*
		 * lexer
		 */
		final org.antlr.v4.runtime.ANTLRInputStream antlrInputStream = new org.antlr.v4.runtime.ANTLRInputStream(
				preProcessedInput);
		final org.cobol85.Cobol85Lexer lexer = new org.cobol85.Cobol85Lexer(antlrInputStream);
		final org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);

		/*
		 * parser
		 */
		final org.cobol85.Cobol85Parser parser = new org.cobol85.Cobol85Parser(tokens);
		final org.cobol85.Cobol85Parser.StartRuleContext ctx = parser.startRule();

		/*
		 * traverse the abstract syntax tree (AST) with an ANTLR visitor
		 */
		
        ParseTreeWalker walker = new ParseTreeWalker();
        MyListener listener=new MyListener();
        walker.walk(listener, ctx);

	}

}
