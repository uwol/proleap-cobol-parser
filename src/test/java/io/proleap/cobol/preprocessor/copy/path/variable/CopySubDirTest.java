package io.proleap.cobol.preprocessor.copy.path.variable;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;

public class CopySubDirTest {

	private static final String DIR = "src/test/resources/io/proleap/cobol/preprocessor/copy/path/variable";

	@Test
	public void testCopyBooks() throws Exception {
		final File inputFile = new File(DIR + "/CopySubDir.cbl");
		final File copyFile1 = new File(DIR + "/copybooks/somedir/CopyBook1.cpy");
		final File copyFile2 = new File(DIR + "/copybooks/somedir/CopyBook2.cpy");
		final File copyFile3 = new File(DIR + "/copybooks/somedir/CopyBook3.cpy");
		final File copyFile4 = new File(DIR + "/copybooks/somedir/CopyBook4.cpy");
		final File copyFile5 = new File(DIR + "/copybooks/somedir/CopyBook5.cpy");
		final ArrayList<File> copyFiles = Lists.newArrayList(copyFile1, copyFile2, copyFile3, copyFile4, copyFile5);

		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, copyFiles,
				CobolSourceFormatEnum.VARIABLE);

		final File expectedFile = new File(DIR + "/CopySubDir.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}

	@Test
	public void testCopyDirs() throws Exception {
		final File inputFile = new File(DIR + "/CopySubDir.cbl");
		final File copyDir = new File(DIR + "/copybooks");
		final ArrayList<File> copyDirs = Lists.newArrayList(copyDir);

		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, copyDirs,
				CobolSourceFormatEnum.VARIABLE);

		final File expectedFile = new File(DIR + "/CopySubDir.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}