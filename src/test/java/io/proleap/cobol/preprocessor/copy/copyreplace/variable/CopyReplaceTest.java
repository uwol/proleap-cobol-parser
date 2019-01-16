package io.proleap.cobol.preprocessor.copy.copyreplace.variable;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.asg.params.impl.CobolParserParamsImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;

public class CopyReplaceTest {

	private static final String DIR = "src/test/resources/io/proleap/cobol/preprocessor/copy/copyreplace/variable";

	@Test
	public void testCopyBookDirectories() throws Exception {
		final File copyBookDirectory = new File(DIR + "/copybooks");
		final List<File> copyBookDirectories = Arrays.asList(copyBookDirectory);

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setCopyBookDirectories(copyBookDirectories);
		params.setFormat(CobolSourceFormatEnum.VARIABLE);

		final File inputFile = new File(DIR + "/CopyReplace.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, params);

		final File expectedFile = new File(DIR + "/CopyReplace.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile, StandardCharsets.UTF_8);
		assertEquals(expected, preProcessedInput);
	}

	@Test
	public void testCopyBookFiles() throws Exception {
		final File copyBookFile1 = new File(DIR + "/copybooks/CopyReplace1.cpy");
		final File copyBookFile2 = new File(DIR + "/copybooks/CopyReplace2.cpy");
		final List<File> copyBookFiles = Arrays.asList(copyBookFile1, copyBookFile2);

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setCopyBookFiles(copyBookFiles);
		params.setFormat(CobolSourceFormatEnum.VARIABLE);

		final File inputFile = new File(DIR + "/CopyReplace.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, params);

		final File expectedFile = new File(DIR + "/CopyReplace.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile, StandardCharsets.UTF_8);
		assertEquals(expected, preProcessedInput);
	}
}