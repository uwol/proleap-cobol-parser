package io.proleap.cobol.preprocessor.copy.copyreplace.variable;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;

public class CopyReplaceTest {

	private static final String DIR = "src/test/resources/io/proleap/cobol/preprocessor/copy/copyreplace/variable";

	@Test
	public void testCopyBooks() throws Exception {
		final File inputFile = new File(DIR + "/CopyReplace.cbl");
		final File copyFile1 = new File(DIR + "/copybooks/CopyReplace1.cpy");
		final File copyFile2 = new File(DIR + "/copybooks/CopyReplace2.cpy");
		final ArrayList<File> copyFiles = Lists.newArrayList(copyFile1, copyFile2);

		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, copyFiles,
				CobolSourceFormatEnum.VARIABLE);

		final File expectedFile = new File(DIR + "/CopyReplace.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}

	@Test
	public void testCopyDir() throws Exception {
		final File inputFile = new File(DIR + "/CopyReplace.cbl");
		final File copyDir = new File(DIR + "/copybooks");
		final ArrayList<File> copyFiles = Lists.newArrayList(copyDir);

		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, copyFiles,
				CobolSourceFormatEnum.VARIABLE);

		final File expectedFile = new File(DIR + "/CopyReplace.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}