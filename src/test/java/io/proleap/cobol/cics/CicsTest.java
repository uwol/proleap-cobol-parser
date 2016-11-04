package io.proleap.cobol.cics;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.applicationcontext.CobolGrammarContextFactory;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.runner.CobolParseTestRunner;
import io.proleap.cobol.runner.impl.CobolParseTestRunnerImpl;

public class CicsTest {

	@Test
	public void test() throws Exception {
		CobolGrammarContextFactory.configureDefaultApplicationContext();

		final String testDirectoryString = System.getProperty("testDirectory", "src/test/resources/io/proleap/cobol/");
		final File inputDirectory = new File(testDirectoryString, "cics");
		final CobolParseTestRunner runner = new CobolParseTestRunnerImpl();
		runner.parseDirectory(inputDirectory, CobolSourceFormatEnum.VARIABLE);
	}
}