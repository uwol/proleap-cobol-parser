package io.proleap.cobol.preprocessor.copy.suppress.fixed;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;

public class CopySuppressTest {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/preprocessor/copy/suppress/fixed/CopySuppress.cbl");
		final File copyFile1 = new File(
				"src/test/resources/io/proleap/cobol/preprocessor/copy/suppress/fixed/CopyReplace.cpy");
		final ArrayList<File> copyFiles = Lists.newArrayList(copyFile1);

		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, copyFiles,
				CobolSourceFormatEnum.FIXED);

		final File expectedFile = new File(
				"src/test/resources/io/proleap/cobol/preprocessor/copy/suppress/fixed/CopySuppress.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}