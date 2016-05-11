package my.test;

import java.io.IOException;

public class Esempio1 {

	public static void main(String[] args) throws IOException {

		org.cobol85.applicationcontext.Cobol85GrammarContextFactory.configureDefaultApplicationContext();
		
		//20160501 AC
		String fname="main/java/my/test/myCM101M.CBL"; //"test/resources/org/cobol85/gpl/HelloWorldVar.cbl";
		
		final java.io.File inputFile = new java.io.File("src/"+fname);
		final java.io.File libDirectory = inputFile.getParentFile();

		/*
		 * COBOL preprocessor
		 */
		// if automatic detection of COBOL line format fails, it has to be set
		// here
		final org.cobol85.preprocessor.Cobol85Preprocessor.Cobol85FormatEnum[] formats = null;
		final String preProcessedInput = org.cobol85.applicationcontext.Cobol85GrammarContext.getInstance()
				.getCobol85Preprocessor().process(inputFile, libDirectory, formats);

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
		final org.cobol85.Cobol85BaseVisitor<Boolean> visitor = new org.cobol85.Cobol85BaseVisitor<Boolean>() {
			/*
			 * exemplary callback function for identification division
			 */
			@Override
			public Boolean visitIdentificationDivision(
					final org.cobol85.Cobol85Parser.IdentificationDivisionContext ctx) {
				return visitChildren(ctx);
			}
			
			//20160501 AC
			@Override
			public Boolean visitDisplayStatement(
					final org.cobol85.Cobol85Parser.DisplayStatementContext ctx) {
				System.out.println("Display " +ctx.literal(0).getText());
				return visitChildren(ctx);
			}
			
		};

		visitor.visit(ctx);
	}

}
