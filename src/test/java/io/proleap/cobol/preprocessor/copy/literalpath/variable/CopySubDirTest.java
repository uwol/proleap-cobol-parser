package io.proleap.cobol.preprocessor.copy.literalpath.variable;

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

public class CopySubDirTest {

	private static final String DIR = "src/test/resources/io/proleap/cobol/preprocessor/copy/literalpath/variable";

	@Test
	public void testCopyBookDirectories() throws Exception {
		final File copyBookDirectory = new File(DIR + "/copybooks");
		final ArrayList<File> copyBookDirectories = Lists.newArrayList(copyBookDirectory);

		final CobolPreprocessorParams params = new CobolPreprocessorParamsImpl();
		params.setCopyBookDirectories(copyBookDirectories);

		final File inputFile = new File(DIR + "/CopySubDir.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, CobolSourceFormatEnum.VARIABLE,
				params);

		final File expectedFile = new File(DIR + "/CopySubDir.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}

	@Test
	public void testCopyBookFiles() throws Exception {
		final File copyBookFile1 = new File(DIR + "/copybooks/somedir/CopyBook1.cpy");
		final File copyBookFile2 = new File(DIR + "/copybooks/somedir/CopyBook2.cpy");
		final File copyBookFile3 = new File(DIR + "/copybooks/somedir/CopyBook3.cpy");
		final File copyBookFile4 = new File(DIR + "/copybooks/somedir/CopyBook4.cpy");
		final File copyBookFile5 = new File(DIR + "/copybooks/somedir/CopyBook5.cpy");
		final ArrayList<File> copyBookFiles = Lists.newArrayList(copyBookFile1, copyBookFile2, copyBookFile3,
				copyBookFile4, copyBookFile5);

		final CobolPreprocessorParams params = new CobolPreprocessorParamsImpl();
		params.setCopyBookFiles(copyBookFiles);

		final File inputFile = new File(DIR + "/CopySubDir.cbl");
		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, CobolSourceFormatEnum.VARIABLE,
				params);

		final File expectedFile = new File(DIR + "/CopySubDir.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}