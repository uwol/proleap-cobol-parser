/*
Copyright (C) 2015 u.wol@wwu.de

This file is part of cobol85grammar.

cobol85grammar is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

cobol85grammar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with cobol85grammar. If not, see <http://www.gnu.org/licenses/>.
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

	private final static String[] extensions = new String[] { "cbl", "cob", "jcl", "txt", "" };

	private final static File inputDirectory = new File("src/test/resources/org/cobol85");

	private final static Logger LOG = LogManager.getLogger(TestGenerator.class);

	private final static File outputDirectory = new File("src/test/java/org/cobol85");

	public static String firstToUpper(final String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	public static void generateTestClass(final File vb6InputFile, final File outputDirectory, final String packageName)
			throws IOException {
		if (vb6InputFile.isFile() && !vb6InputFile.isHidden()) {
			final String inputFilename = firstToUpper(FilenameUtils.removeExtension(vb6InputFile.getName()));

			final File outputFile = new File(outputDirectory + "/" + inputFilename + "Test.java");

			LOG.info("Creating {}.", outputFile);
			outputFile.createNewFile();

			final PrintWriter pWriter = new PrintWriter(new FileWriter(outputFile));

			final String vb6InputFileName = vb6InputFile.getPath().replace("\\", "/");

			pWriter.write("package " + packageName + ";\n");
			pWriter.write("\n");
			pWriter.write("import java.io.File;\n");
			pWriter.write("\n");
			pWriter.write("import org.junit.Test;\n");
			pWriter.write("import org.cobol85.applicationcontext.Cobol85GrammarContextFactory;\n");
			pWriter.write("import org.cobol85.runner.Cobol85ParseTestRunner;\n");
			pWriter.write("import org.cobol85.runner.impl.Cobol85ParseTestRunnerImpl;\n");
			pWriter.write("\n");
			pWriter.write("public class " + inputFilename + "Test {\n");
			pWriter.write("\n");
			pWriter.write("	@Test\n");
			pWriter.write("	public void test() throws Exception {\n");
			pWriter.write("		Cobol85GrammarContextFactory.configureDefaultApplicationContext();\n");
			pWriter.write("\n");
			pWriter.write("		final File inputFile = new File(\"" + vb6InputFileName + "\");\n");
			pWriter.write("		final Cobol85ParseTestRunner runner = new Cobol85ParseTestRunnerImpl();\n");
			pWriter.write("		runner.parseFile(inputFile);\n");
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
				if (isCobolFile(inputDirectoryFile)) {
					generateTestClass(inputDirectoryFile, outputDirectory, packageName);
				}
				// else, if the file is a directory
				else if (inputDirectoryFile.isDirectory()) {
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

	protected static boolean isCobolFile(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return inputFile.isFile() && Arrays.asList(extensions).contains(extension);
	}

	public static void main(final String[] args) throws IOException {
		generateTestClasses(inputDirectory, outputDirectory, "org.cobol85");
	}
}
