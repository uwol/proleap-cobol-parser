package org.cobol85.gpl.lineformat;

import java.io.File;

import org.junit.Test;
import org.cobol85.applicationcontext.Cobol85GrammarContextFactory;
import org.cobol85.preprocessor.Cobol85Preprocessor.Cobol85SourceFormatEnum;
import org.cobol85.runner.Cobol85ParseTestRunner;
import org.cobol85.runner.impl.Cobol85ParseTestRunnerImpl;

public class FixedTest {

	@Test
	public void test() throws Exception {
		Cobol85GrammarContextFactory.configureDefaultApplicationContext();

		final File inputFile = new File("src/test/resources/org/cobol85/gpl/lineformat/Fixed.cbl");
		final Cobol85ParseTestRunner runner = new Cobol85ParseTestRunnerImpl();
		runner.parseFile(inputFile, Cobol85SourceFormatEnum.FIXED);
	}
}