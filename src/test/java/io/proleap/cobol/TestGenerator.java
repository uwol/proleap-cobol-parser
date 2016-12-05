/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import io.proleap.cobol.Cobol85Parser.StartRuleContext;
import io.proleap.cobol.applicationcontext.CobolGrammarContext;
import io.proleap.cobol.applicationcontext.CobolGrammarContextFactory;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormat;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.util.TreeUtils;

public class TestGenerator {

	private final static String[] COBOL_FILE_EXTENSIONS = new String[] { "cbl", "cob", "jcl", "txt", "" };

	private final static String[] DIRECTORIES_EXCLUDED = new String[] { "nist", "asg" };

	private final static File INPUT_DIRECTORY = new File("src/test/resources");

	private static final String JAVA_EXTENSION = ".java";

	private final static Logger LOG = LogManager.getLogger(TestGenerator.class);

	private final static File OUTPUT_DIRECTORY = new File("src/test/java");

	private final static String OUTPUT_FILE_SUFFIX = "Test";

	private static final String TREE_EXTENSION = ".tree";

	public static String firstToUpper(final String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	public static void generateTestClass(final File cobolInputFile, final File outputDirectory,
			final String packageName) throws IOException {
		final File parentDirectory = cobolInputFile.getParentFile();
		final String inputFilename = getInputFilename(cobolInputFile);
		final File outputFile = new File(outputDirectory + "/" + inputFilename + OUTPUT_FILE_SUFFIX + JAVA_EXTENSION);

		final boolean createdNewFile = outputFile.createNewFile();

		if (createdNewFile) {
			LOG.info("Creating unit test {}.", outputFile);

			final PrintWriter pWriter = new PrintWriter(new FileWriter(outputFile));
			final String cobolInputFileName = cobolInputFile.getPath().replace("\\", "/");
			final CobolSourceFormat format = getCobolSourceFormat(parentDirectory);

			pWriter.write("package " + packageName + ";\n");
			pWriter.write("\n");
			pWriter.write("import java.io.File;\n");
			pWriter.write("\n");
			pWriter.write("import io.proleap.cobol.applicationcontext.CobolGrammarContextFactory;\n");
			pWriter.write("import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;\n");
			pWriter.write("import io.proleap.cobol.runner.CobolParseTestRunner;\n");
			pWriter.write("import io.proleap.cobol.runner.impl.CobolParseTestRunnerImpl;\n");
			pWriter.write("import org.junit.Test;\n");
			pWriter.write("\n");
			pWriter.write("public class " + inputFilename + "Test {\n");
			pWriter.write("\n");
			pWriter.write("	@Test\n");
			pWriter.write("	public void test() throws Exception {\n");
			pWriter.write("		CobolGrammarContextFactory.configureDefaultApplicationContext();\n");
			pWriter.write("\n");
			pWriter.write("		final File inputFile = new File(\"" + cobolInputFileName + "\");\n");
			pWriter.write("		final CobolParseTestRunner runner = new CobolParseTestRunnerImpl();\n");
			pWriter.write("		runner.parseFile(inputFile, CobolSourceFormatEnum." + format + ");\n");
			pWriter.write("	}\n");
			pWriter.write("}");

			pWriter.flush();
			pWriter.close();
		}
	}

	public static void generateTestClasses(final File inputDirectory, final File outputDirectory,
			final String packageName) throws IOException {
		final String outputDirectoryPath = outputDirectory.getPath();

		if (inputDirectory.isDirectory()) {
			// for each of the files in the directory
			for (final File inputDirectoryFile : inputDirectory.listFiles()) {
				// if the file is a Cobol relevant file
				if (inputDirectoryFile.isFile() && !inputDirectoryFile.isHidden() && isCobolFile(inputDirectoryFile)) {
					generateTestClass(inputDirectoryFile, outputDirectory, packageName);
					generateTreeFile(inputDirectoryFile, inputDirectory);
				}
				// else, if the file is a relevant directory
				else if (inputDirectoryFile.isDirectory() && !isDirectoryExcluded(inputDirectoryFile)) {
					final File subInputDirectory = inputDirectoryFile;
					final String subInputDirectoryName = subInputDirectory.getName();

					if (!".".equals(subInputDirectoryName) && !"..".equals(subInputDirectoryName)) {
						/*
						 * determine the output directory, where test classes
						 * should be placed
						 */
						final File subOutputDirectory = new File(outputDirectoryPath + "/" + subInputDirectoryName);
						subOutputDirectory.mkdirs();

						// determine the package name of test classes
						final String subPackageName = Strings.isBlank(packageName) ? subInputDirectoryName
								: packageName + "." + subInputDirectoryName;

						generateTestClasses(subInputDirectory, subOutputDirectory, subPackageName);
					}
				}
			}
		}
	}

	public static void generateTreeFile(final File cobolInputFile, final File outputDirectory) throws IOException {
		final File outputFile = new File(outputDirectory + "/" + cobolInputFile.getName() + TREE_EXTENSION);

		final boolean createdNewFile = outputFile.createNewFile();

		if (createdNewFile) {
			LOG.info("Creating tree file {}.", outputFile);

			final File parentDirectory = cobolInputFile.getParentFile();
			final CobolSourceFormat format = getCobolSourceFormat(parentDirectory);

			final String preProcessedInput = CobolGrammarContext.getInstance().getCobolPreprocessor()
					.process(cobolInputFile, parentDirectory, format);

			final Cobol85Lexer lexer = new Cobol85Lexer(new ANTLRInputStream(preProcessedInput));
			final CommonTokenStream tokens = new CommonTokenStream(lexer);
			final Cobol85Parser parser = new Cobol85Parser(tokens);
			final StartRuleContext startRule = parser.startRule();
			final String inputFileTree = TreeUtils.toStringTree(startRule, parser);

			final PrintWriter pWriter = new PrintWriter(new FileWriter(outputFile));

			pWriter.write(inputFileTree);
			pWriter.flush();
			pWriter.close();
		}
	}

	protected static CobolSourceFormat getCobolSourceFormat(final File directory) {
		final String parentDirectoryName = directory.getName();
		final CobolSourceFormat result;

		switch (parentDirectoryName) {
		case "fixed":
			result = CobolSourceFormatEnum.FIXED;
			break;
		case "tandem":
			result = CobolSourceFormatEnum.TANDEM;
			break;
		case "variable":
			result = CobolSourceFormatEnum.VARIABLE;
			break;
		default:
			result = CobolSourceFormatEnum.FIXED;
		}

		return result;
	}

	protected static String getInputFilename(final File inputFile) {
		final String result = firstToUpper(FilenameUtils.removeExtension(inputFile.getName()));
		return result;
	}

	protected static boolean isCobolFile(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return inputFile.isFile() && Arrays.asList(COBOL_FILE_EXTENSIONS).contains(extension);
	}

	protected static boolean isDirectoryExcluded(final File directory) {
		final String directoryName = directory.getName();
		return Arrays.asList(DIRECTORIES_EXCLUDED).contains(directoryName);
	}

	public static void main(final String[] args) throws IOException {
		CobolGrammarContextFactory.configureDefaultApplicationContext();

		generateTestClasses(INPUT_DIRECTORY, OUTPUT_DIRECTORY, "");
	}
}
