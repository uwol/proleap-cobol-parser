package io.proleap.cobol.preprocessor.variable;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;

public class ExecSqlImsTest {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/preprocessor/variable/ExecSqlIms.cbl");
		final File libDirectory = inputFile.getParentFile();
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, libDirectory,
				CobolSourceFormatEnum.VARIABLE);

		final File expectedFile = new File(
				"src/test/resources/io/proleap/cobol/preprocessor/variable/ExecSqlIms.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}