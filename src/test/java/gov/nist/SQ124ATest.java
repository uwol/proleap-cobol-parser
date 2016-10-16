package gov.nist;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.applicationcontext.Cobol85GrammarContextFactory;
import io.proleap.cobol.preprocessor.Cobol85Preprocessor.Cobol85SourceFormatEnum;
import io.proleap.cobol.runner.Cobol85ParseTestRunner;
import io.proleap.cobol.runner.impl.Cobol85ParseTestRunnerImpl;

public class SQ124ATest {

	@Test
	public void test() throws Exception {
		Cobol85GrammarContextFactory.configureDefaultApplicationContext();

		final File inputFile = new File("src/test/resources/gov/nist/SQ124A.CBL");
		final Cobol85ParseTestRunner runner = new Cobol85ParseTestRunnerImpl();
		runner.parseFile(inputFile, Cobol85SourceFormatEnum.FIXED);
	}
}