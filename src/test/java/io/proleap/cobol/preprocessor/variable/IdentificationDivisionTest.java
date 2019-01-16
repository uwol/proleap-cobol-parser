package io.proleap.cobol.preprocessor.variable;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.asg.params.impl.CobolParserParamsImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;

public class IdentificationDivisionTest {

	@Test
	public void test() throws Exception {
		final CobolParserParams params = new CobolParserParamsImpl();
		params.setFormat(CobolSourceFormatEnum.VARIABLE);

		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/preprocessor/variable/IdentificationDivision.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, params);

		final File expectedFile = new File(
				"src/test/resources/io/proleap/cobol/preprocessor/variable/IdentificationDivision.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile, StandardCharsets.UTF_8);
		assertEquals(expected, preProcessedInput);
	}
}