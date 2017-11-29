package io.proleap.cobol.preprocessor.copy.extension.precedence.variable;

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

public class CopyPrecedenceTest {

	private static final String DIR = "src/test/resources/io/proleap/cobol/preprocessor/copy/extension/precedence/variable";

	@Test
	public void testCopyBooks() throws Exception {
		final File inputFile = new File(DIR + "/CopyPrecedence.cbl");
		final File copyFileNo = new File(DIR + "/copybooks/SomeCopyBook");
		final File copyFileCbl = new File(DIR + "/copybooks/SomeCopyBook.cbl");
		final File copyFileTxt = new File(DIR + "/copybooks/SomeCopyBook.txt");
		final ArrayList<File> copyFiles = Lists.newArrayList(copyFileNo, copyFileCbl, copyFileTxt);

		final CobolPreprocessorParams params = new CobolPreprocessorParamsImpl();
		params.setCopyBookExtensions(Lists.newArrayList("someotherextension", "txt", "cbl"));

		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, copyFiles,
				CobolSourceFormatEnum.FIXED, params);

		final File expectedFile = new File(DIR + "/CopyPrecedence.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}

	@Test
	public void testCopyDir() throws Exception {
		final File inputFile = new File(DIR + "/CopyPrecedence.cbl");
		final File copyDir = new File(DIR + "/copybooks");
		final ArrayList<File> copyDirs = Lists.newArrayList(copyDir);

		final CobolPreprocessorParams params = new CobolPreprocessorParamsImpl();
		params.setCopyBookExtensions(Lists.newArrayList("someotherextension", "txt", "cbl"));

		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, copyDirs,
				CobolSourceFormatEnum.FIXED, params);

		final File expectedFile = new File(DIR + "/CopyPrecedence.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}