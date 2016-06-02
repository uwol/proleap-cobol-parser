package org.cobol85.cics;

import java.io.File;

import org.cobol85.applicationcontext.Cobol85GrammarContextFactory;
import org.cobol85.preprocessor.Cobol85Preprocessor.Cobol85SourceFormatEnum;
import org.cobol85.runner.Cobol85ParseTestRunner;
import org.cobol85.runner.impl.Cobol85ParseTestRunnerImpl;
import org.junit.Test;

public class CicsTest {

	@Test
	public void test() throws Exception {
		Cobol85GrammarContextFactory.configureDefaultApplicationContext();

		final String testDirectoryString = System.getProperty("testDirectory", "src/test/resources/org/cobol85/");
		final File inputDirectory = new File(testDirectoryString, "cics");
		final Cobol85ParseTestRunner runner = new Cobol85ParseTestRunnerImpl();
		runner.parseDirectory(inputDirectory, Cobol85SourceFormatEnum.VARIABLE);
	}
}