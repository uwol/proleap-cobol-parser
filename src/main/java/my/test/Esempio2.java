package my.test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Esempio2 {
	private final static String[] cobolFileExtensions = new String[] { "cbl", "cob", "jcl", "txt", "" };

	private final static Logger LOG = LogManager.getLogger(Esempio2.class);
	
	private static boolean isCobolFile(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return inputFile.isFile() && Arrays.asList(cobolFileExtensions).contains(extension);
	}
	
	public static void parseDirectory(final File inputDirectory) throws IOException {
		if (inputDirectory.isDirectory() && !inputDirectory.isHidden()) {
			for (final File inputFile : inputDirectory.listFiles()) {
				if (inputFile.isFile() && !inputFile.isHidden() && isCobolFile(inputFile)) {
					parseFile(inputFile);
				}
			}
		}
	}
	
	private static void parseFile(File inputFile) throws IOException {
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
		// register an error listener, so that preprocessing stops on errors
		parser.removeErrorListeners();
		parser.addErrorListener(new myThrowingErrorListener());
		
		final org.cobol85.Cobol85Parser.StartRuleContext ctx = parser.startRule();
		/*
		 * traverse the abstract syntax tree (AST) with an ANTLR visitor
		 */
        ParseTreeWalker walker = new ParseTreeWalker();
        MyListener listener=new MyListener();
        try {
        	listener.out.normalizedinput=preProcessedInput;
        	listener.out.filename=inputFile.getName();
        	walker.walk(listener, ctx);
        } catch (RuntimeException e) {
        	listener.out.ErrorMessage=e.getMessage();
        }
        
        //TODO tracciare le eccezioni e dump dell'if etc.
        System.out.println(listener.out);
        
	}

	private void test() throws IOException{
		//20160501 AC
		String fname="d:\\docs\\coboldocs\\TEST6.COB"; //"test/resources/org/cobol85/gpl/HelloWorldVar.cbl";
		//String fname="D:\\docs\\intellijwork\\testcobol\\src\\Replace_bug.cbl";
		final java.io.File inputFile = new java.io.File(fname);
		parseFile(inputFile);
	}
	
	private static void init() {
		org.cobol85.applicationcontext.Cobol85GrammarContextFactory.configureDefaultApplicationContext();
	}
	
	public static void main(String[] args)  {
		init();
		String fname="D:\\docs\\cobolDocs\\testdir";
		fname+="\\ZGEGRI00.cob";
		final java.io.File inputDir = new java.io.File(fname);
		//parseDirectory(inputDir);
		parseFile(inputDir);
	}

}
