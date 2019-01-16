package io.proleap.cobol.preprocessor.copy.copyof;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.asg.params.impl.CobolParserParamsImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;

public class CopyOfTest {

	private static final String DIR = "src/test/resources/io/proleap/cobol/preprocessor/copy/copyof";

	@Ignore
	@Test
	public void testCopyBookDirectories() throws Exception {
		final File copyBookDirectory = new File(DIR + "/copybooks");
		final List<File> copyBookDirectories = Arrays.asList(copyBookDirectory);

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setCopyBookDirectories(copyBookDirectories);
		params.setFormat(CobolSourceFormatEnum.VARIABLE);

		final File inputFile = new File(DIR + "/CopyOf.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, params);

		final File expectedFile = new File(DIR + "/CopyOf.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile, StandardCharsets.UTF_8);
		assertEquals(expected, preProcessedInput);
	}

	@Ignore
	@Test
	public void testCopyBookFiles() throws Exception {
		final File copyBookFile1 = new File(DIR + "/copybooks/CopyOf1.cpy");
		final List<File> copyBookFiles = Arrays.asList(copyBookFile1);

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setCopyBookFiles(copyBookFiles);
		params.setFormat(CobolSourceFormatEnum.VARIABLE);

		final File inputFile = new File(DIR + "/CopyOf.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, params);

		final File expectedFile = new File(DIR + "/CopyOf.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile, StandardCharsets.UTF_8);
		assertEquals(expected, preProcessedInput);
	}
}