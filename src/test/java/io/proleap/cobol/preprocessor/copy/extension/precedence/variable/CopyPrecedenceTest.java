package io.proleap.cobol.preprocessor.copy.extension.precedence.variable;

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

public class CopyPrecedenceTest {

	private static final String DIR = "src/test/resources/io/proleap/cobol/preprocessor/copy/extension/precedence/variable";

	@Test
	public void testCopyBookDirectories() throws Exception {
		final File copyBookDirectory = new File(DIR + "/copybooks");
		final ArrayList<File> copyBookDirectories = Lists.newArrayList(copyBookDirectory);

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setCopyBookDirectories(copyBookDirectories);
		params.setCopyBookExtensions(Lists.newArrayList("someotherextension", "txt", "cbl"));

		final File inputFile = new File(DIR + "/CopyPrecedence.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, CobolSourceFormatEnum.FIXED,
				params);

		final File expectedFile = new File(DIR + "/CopyPrecedence.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}

	@Test
	public void testCopyBookFiles() throws Exception {
		final File copyBookFile1 = new File(DIR + "/copybooks/SomeCopyBook");
		final File copyBookFile2 = new File(DIR + "/copybooks/SomeCopyBook.cbl");
		final File copyBookFile3 = new File(DIR + "/copybooks/SomeCopyBook.txt");
		final ArrayList<File> copyBookFiles = Lists.newArrayList(copyBookFile1, copyBookFile2, copyBookFile3);

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setCopyBookFiles(copyBookFiles);
		params.setCopyBookExtensions(Lists.newArrayList("someotherextension", "txt", "cbl"));

		final File inputFile = new File(DIR + "/CopyPrecedence.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, CobolSourceFormatEnum.FIXED,
				params);

		final File expectedFile = new File(DIR + "/CopyPrecedence.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}
