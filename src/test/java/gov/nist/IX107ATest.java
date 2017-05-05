package gov.nist;

import java.io.File;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.runner.CobolParseTestRunner;
import io.proleap.cobol.runner.impl.CobolParseTestRunnerImpl;
import org.junit.Test;

public class IX107ATest {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/gov/nist/IX107A.CBL");
		final CobolParseTestRunner runner = new CobolParseTestRunnerImpl();
		runner.parseFile(inputFile, CobolSourceFormatEnum.FIXED);
	}
}