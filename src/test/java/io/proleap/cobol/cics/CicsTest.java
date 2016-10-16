package io.proleap.cobol.cics;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.applicationcontext.Cobol85GrammarContextFactory;
import io.proleap.cobol.preprocessor.Cobol85Preprocessor.Cobol85SourceFormatEnum;
import io.proleap.cobol.runner.Cobol85ParseTestRunner;
import io.proleap.cobol.runner.impl.Cobol85ParseTestRunnerImpl;

public class CicsTest {

	@Test
	public void test() throws Exception {
		Cobol85GrammarContextFactory.configureDefaultApplicationContext();

		final String testDirectoryString = System.getProperty("testDirectory", "src/test/resources/io/proleap/cobol/");
		final File inputDirectory = new File(testDirectoryString, "cics");
		final Cobol85ParseTestRunner runner = new Cobol85ParseTestRunnerImpl();
		runner.parseDirectory(inputDirectory, Cobol85SourceFormatEnum.VARIABLE);
	}
}