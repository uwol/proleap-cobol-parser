/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package org.cobol85;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.Trees;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cobol85.Cobol85Parser.StartRuleContext;
import org.cobol85.applicationcontext.Cobol85GrammarContext;
import org.cobol85.applicationcontext.Cobol85GrammarContextFactory;
import org.cobol85.preprocessor.Cobol85Preprocessor.Cobol85SourceFormat;
import org.cobol85.preprocessor.Cobol85Preprocessor.Cobol85SourceFormatEnum;

public class TestGenerator {

	private final static String[] COBOL_FILE_EXTENSIONS = new String[] { "cbl", "cob", "jcl", "txt", "" };

	private final static String[] DIRECTORIES_EXCLUDED = new String[] { "cics" };

	private final static File INPUT_DIRECTORY = new File("src/test/resources/org/cobol85");

	private static final String JAVA_EXTENSION = ".java";

	private final static Logger LOG = LogManager.getLogger(TestGenerator.class);

	private final static File OUTPUT_DIRECTORY = new File("src/test/java/org/cobol85");

	private final static String OUTPUT_FILE_SUFFIX = "Test";

	private static final String TREE_EXTENSION = ".tree";

	public static String firstToUpper(final String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	public static void generateTestClass(final File cobol85InputFile, final File outputDirectory,
			final String packageName) throws IOException {
		final File parentDirectory = cobol85InputFile.getParentFile();
		final String inputFilename = getInputFilename(cobol85InputFile);
		final File outputFile = new File(outputDirectory + "/" + inputFilename + OUTPUT_FILE_SUFFIX + JAVA_EXTENSION);

		LOG.info("Creating unit test {}.", outputFile);
		final boolean createdNewFile = outputFile.createNewFile();

		if (createdNewFile) {
			final PrintWriter pWriter = new PrintWriter(new FileWriter(outputFile));
			final String cobol85InputFileName = cobol85InputFile.getPath().replace("\\", "/");
			final Cobol85SourceFormat format = getCobol85SourceFormat(parentDirectory);

			pWriter.write("package " + packageName + ";\n");
			pWriter.write("\n");
			pWriter.write("import java.io.File;\n");
			pWriter.write("\n");
			pWriter.write("import org.cobol85.applicationcontext.Cobol85GrammarContextFactory;\n");
			pWriter.write("import org.cobol85.preprocessor.Cobol85Preprocessor.Cobol85SourceFormatEnum;\n");
			pWriter.write("import org.cobol85.runner.Cobol85ParseTestRunner;\n");
			pWriter.write("import org.cobol85.runner.impl.Cobol85ParseTestRunnerImpl;\n");
			pWriter.write("import org.junit.Test;\n");
			pWriter.write("\n");
			pWriter.write("public class " + inputFilename + "Test {\n");
			pWriter.write("\n");
			pWriter.write("	@Test\n");
			pWriter.write("	public void test() throws Exception {\n");
			pWriter.write("		Cobol85GrammarContextFactory.configureDefaultApplicationContext();\n");
			pWriter.write("\n");
			pWriter.write("		final File inputFile = new File(\"" + cobol85InputFileName + "\");\n");
			pWriter.write("		final Cobol85ParseTestRunner runner = new Cobol85ParseTestRunnerImpl();\n");
			pWriter.write("		runner.parseFile(inputFile, Cobol85SourceFormatEnum." + format + ");\n");
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
				// if the file is a Cobol85 relevant file
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
						final String subPackageName = packageName + "." + subInputDirectoryName;

						generateTestClasses(subInputDirectory, subOutputDirectory, subPackageName);
					}
				}
			}
		}
	}

	public static void generateTreeFile(final File cobol85InputFile, final File outputDirectory) throws IOException {
		final File outputFile = new File(outputDirectory + "/" + cobol85InputFile.getName() + TREE_EXTENSION);

		LOG.info("Creating tree file {}.", outputFile);
		final boolean createdNewFile = outputFile.createNewFile();

		if (createdNewFile) {
			final File parentDirectory = cobol85InputFile.getParentFile();
			final Cobol85SourceFormat format = getCobol85SourceFormat(parentDirectory);

			final String preProcessedInput = Cobol85GrammarContext.getInstance().getCobol85Preprocessor()
					.process(cobol85InputFile, parentDirectory, null, format);

			final Cobol85Lexer lexer = new Cobol85Lexer(new ANTLRInputStream(preProcessedInput));
			final CommonTokenStream tokens = new CommonTokenStream(lexer);
			final Cobol85Parser parser = new Cobol85Parser(tokens);
			final StartRuleContext startRule = parser.startRule();
			final String inputFileTree = Trees.toStringTree(startRule, parser);
			final String cleanedInputFileTree = org.cobol85.util.StringUtils.cleanFileTree(inputFileTree);

			final PrintWriter pWriter = new PrintWriter(new FileWriter(outputFile));

			pWriter.write(cleanedInputFileTree);
			pWriter.flush();
			pWriter.close();
		}
	}

	protected static Cobol85SourceFormat getCobol85SourceFormat(final File directory) {
		final String parentDirectoryName = directory.getName();
		final Cobol85SourceFormat result;

		switch (parentDirectoryName) {
		case "fixed":
			result = Cobol85SourceFormatEnum.FIXED;
			break;
		case "tandem":
			result = Cobol85SourceFormatEnum.TANDEM;
			break;
		case "variable":
			result = Cobol85SourceFormatEnum.VARIABLE;
			break;
		default:
			result = Cobol85SourceFormatEnum.FIXED;
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
		Cobol85GrammarContextFactory.configureDefaultApplicationContext();

		generateTestClasses(INPUT_DIRECTORY, OUTPUT_DIRECTORY, "org.cobol85");
	}
}
