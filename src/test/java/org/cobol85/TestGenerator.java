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

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestGenerator {

	private final static String[] COBOL_FILE_EXTENSIONS = new String[] { "cbl", "cob", "jcl", "txt", "" };

	private final static String[] DIRECTORIES_EXCLUDED = new String[] { "cics" };

	private final static File INPUT_DIRECTORY = new File("src/test/resources/org/cobol85");

	private static final String JAVA_EXTENSION = ".java";

	private final static Logger LOG = LogManager.getLogger(TestGenerator.class);

	private final static File OUTPUT_DIRECTORY = new File("src/test/java/org/cobol85");

	private final static String OUTPUT_FILE_SUFFIX = "Test";

	public static String firstToUpper(final String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	public static void generateTestClass(final File cobol85InputFile, final File outputDirectory,
			final String packageName) throws IOException {
		final String inputFilename = getInputFilename(cobol85InputFile);
		final File outputFile = new File(outputDirectory + "/" + inputFilename + OUTPUT_FILE_SUFFIX + JAVA_EXTENSION);

		LOG.info("Creating unit test {}.", outputFile);
		final boolean createdNewFile = outputFile.createNewFile();

		if (createdNewFile) {
			final PrintWriter pWriter = new PrintWriter(new FileWriter(outputFile));
			final String cobol85InputFileName = cobol85InputFile.getPath().replace("\\", "/");

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
			pWriter.write("		runner.parseFile(inputFile, Cobol85SourceFormatEnum.FIXED);\n");
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
		generateTestClasses(INPUT_DIRECTORY, OUTPUT_DIRECTORY, "org.cobol85");
	}
}
