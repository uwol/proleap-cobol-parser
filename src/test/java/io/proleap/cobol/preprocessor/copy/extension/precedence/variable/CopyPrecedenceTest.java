package io.proleap.cobol.preprocessor.copy.extension.precedence.variable;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.CobolPreprocessorParams;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorParamsImpl;

public class CopyPrecedenceTest {

	private static final String DIR = "src/test/resources/io/proleap/cobol/preprocessor/copy/extension/precedence/variable";

	@Test
	public void testCopyDir() throws Exception {
		final File copyBookDirectory = new File(DIR + "/copybooks");
		final ArrayList<File> copyBookDirectories = Lists.newArrayList(copyBookDirectory);

		final CobolPreprocessorParams params = new CobolPreprocessorParamsImpl();
		params.setCopyBookDirectories(copyBookDirectories);
		params.setCopyBookExtensions(Lists.newArrayList("someotherextension", "txt", "cbl"));

		final File inputFile = new File(DIR + "/CopyPrecedence.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, CobolSourceFormatEnum.FIXED,
				params);

		final File expectedFile = new File(DIR + "/CopyPrecedence.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}
