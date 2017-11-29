package io.proleap.cobol.preprocessor.copy.extension.txt.variable;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;
import io.proleap.cobol.preprocessor.params.CobolPreprocessorParams;
import io.proleap.cobol.preprocessor.params.impl.CobolPreprocessorParamsImpl;

public class CopyTxtExtensionTest {

	private static final String DIR = "src/test/resources/io/proleap/cobol/preprocessor/copy/extension/txt/variable";

	@Test
	public void testCopyBooks() throws Exception {
		final File inputFile = new File(DIR + "/CopyTxtExtension.cbl");
		final File copyFile1 = new File(DIR + "/copybooks/SomeCopyBook.txt");
		final ArrayList<File> copyFiles = Lists.newArrayList(copyFile1);

		final CobolPreprocessorParams params = new CobolPreprocessorParamsImpl();
		params.setCopyBookExtensions(Lists.newArrayList("txt"));

		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, copyFiles,
				CobolSourceFormatEnum.FIXED, params);

		final File expectedFile = new File(DIR + "/CopyTxtExtension.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}

	@Test
	public void testCopyDir() throws Exception {
		final File inputFile = new File(DIR + "/CopyTxtExtension.cbl");
		final File copyDir = new File(DIR + "/copybooks");
		final ArrayList<File> copyDirs = Lists.newArrayList(copyDir);

		final CobolPreprocessorParams params = new CobolPreprocessorParamsImpl();
		params.setCopyBookExtensions(Lists.newArrayList("txt"));

		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, copyDirs,
				CobolSourceFormatEnum.FIXED, params);

		final File expectedFile = new File(DIR + "/CopyTxtExtension.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}