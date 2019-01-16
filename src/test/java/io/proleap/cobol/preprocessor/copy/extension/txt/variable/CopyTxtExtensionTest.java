package io.proleap.cobol.preprocessor.copy.extension.txt.variable;

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

public class CopyTxtExtensionTest {

	private static final String DIR = "src/test/resources/io/proleap/cobol/preprocessor/copy/extension/txt/variable";

	@Test
	public void testCopyBookDirectories() throws Exception {
		final File copyBookDirectory = new File(DIR + "/copybooks");
		final List<File> copyBookDirectories = Arrays.asList(copyBookDirectory);

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setCopyBookDirectories(copyBookDirectories);
		params.setCopyBookExtensions(Arrays.asList("txt"));
		params.setFormat(CobolSourceFormatEnum.FIXED);

		final File inputFile = new File(DIR + "/CopyTxtExtension.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, params);

		final File expectedFile = new File(DIR + "/CopyTxtExtension.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile, StandardCharsets.UTF_8);
		assertEquals(expected, preProcessedInput);
	}

	@Test
	public void testCopyBookFiles() throws Exception {
		final File copyBookFile = new File(DIR + "/copybooks/SomeCopyBook.txt");
		final List<File> copyBookFiles = Arrays.asList(copyBookFile);

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setCopyBookFiles(copyBookFiles);
		params.setCopyBookExtensions(Arrays.asList("txt"));
		params.setFormat(CobolSourceFormatEnum.FIXED);

		final File inputFile = new File(DIR + "/CopyTxtExtension.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, params);

		final File expectedFile = new File(DIR + "/CopyTxtExtension.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile, StandardCharsets.UTF_8);
		assertEquals(expected, preProcessedInput);
	}
}