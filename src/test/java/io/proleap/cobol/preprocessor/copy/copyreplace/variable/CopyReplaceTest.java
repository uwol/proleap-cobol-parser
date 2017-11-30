package io.proleap.cobol.preprocessor.copy.copyreplace.variable;

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

public class CopyReplaceTest {

	private static final String DIR = "src/test/resources/io/proleap/cobol/preprocessor/copy/copyreplace/variable";

	@Test
	public void testCopyBooks() throws Exception {
		final File copyBookFile1 = new File(DIR + "/copybooks/CopyReplace1.cpy");
		final File copyBookFile2 = new File(DIR + "/copybooks/CopyReplace2.cpy");
		final ArrayList<File> copyBookFiles = Lists.newArrayList(copyBookFile1, copyBookFile2);

		final CobolPreprocessorParams params = new CobolPreprocessorParamsImpl();
		params.setCopyBookFiles(copyBookFiles);

		final File inputFile = new File(DIR + "/CopyReplace.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, CobolSourceFormatEnum.VARIABLE,
				params);

		final File expectedFile = new File(DIR + "/CopyReplace.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}

	@Test
	public void testCopyDir() throws Exception {
		final File copyBookDirectory = new File(DIR + "/copybooks");
		final ArrayList<File> copyBookDirectories = Lists.newArrayList(copyBookDirectory);

		final CobolPreprocessorParams params = new CobolPreprocessorParamsImpl();
		params.setCopyBookDirectories(copyBookDirectories);

		final File inputFile = new File(DIR + "/CopyReplace.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, CobolSourceFormatEnum.VARIABLE,
				params);

		final File expectedFile = new File(DIR + "/CopyReplace.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}