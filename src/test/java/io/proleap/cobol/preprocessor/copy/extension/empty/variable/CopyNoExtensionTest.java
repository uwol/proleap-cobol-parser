package io.proleap.cobol.preprocessor.copy.extension.empty.variable;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.asg.params.impl.CobolParserParamsImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;

public class CopyNoExtensionTest {

	private static final String DIR = "src/test/resources/io/proleap/cobol/preprocessor/copy/extension/empty/variable";

	@Test
	public void testCopyBookDirectories() throws Exception {
		final File copyBookDirectory = new File(DIR + "/copybooks");
		final ArrayList<File> copyBookDirectories = Lists.newArrayList(copyBookDirectory);

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setCopyBookDirectories(copyBookDirectories);

		final File inputFile = new File(DIR + "/CopyNoExtension.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, CobolSourceFormatEnum.FIXED,
				params);

		final File expectedFile = new File(DIR + "/CopyNoExtension.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}

	@Test
	public void testCopyBookFiles() throws Exception {
		final File copyBookFile = new File(DIR + "/copybooks/SomeCopyBook");
		final ArrayList<File> copyBookFiles = Lists.newArrayList(copyBookFile);

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setCopyBookFiles(copyBookFiles);

		final File inputFile = new File(DIR + "/CopyNoExtension.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, CobolSourceFormatEnum.FIXED,
				params);

		final File expectedFile = new File(DIR + "/CopyNoExtension.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}
