package io.proleap.cobol.asg;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class InvalidKeywordTest extends CobolTestBase {

	@Test(expected = RuntimeException.class)
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/InvalidKeyword.cbl");
		new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);
	}
}