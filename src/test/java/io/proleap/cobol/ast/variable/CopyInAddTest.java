package io.proleap.cobol.ast.variable;

import java.io.File;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.runner.CobolParseTestRunner;
import io.proleap.cobol.runner.impl.CobolParseTestRunnerImpl;
import org.junit.Test;

public class CopyInAddTest {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/ast/variable/CopyInAdd.cbl");
		final CobolParseTestRunner runner = new CobolParseTestRunnerImpl();
		runner.parseFile(inputFile, CobolSourceFormatEnum.VARIABLE);
	}
}