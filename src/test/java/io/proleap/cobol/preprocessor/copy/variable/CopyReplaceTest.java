package io.proleap.cobol.preprocessor.copy.variable;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;

public class CopyReplaceTest {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/preprocessor/copy/variable/CopyReplace.cbl");
		final File copyFile1 = new File(
				"src/test/resources/io/proleap/cobol/preprocessor/copy/variable/CopyReplace1.cpy");
		final File copyFile2 = new File(
				"src/test/resources/io/proleap/cobol/preprocessor/copy/variable/CopyReplace2.cpy");
		final ArrayList<File> copyFiles = Lists.newArrayList(copyFile1, copyFile2);

		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, copyFiles,
				CobolSourceFormatEnum.VARIABLE);

		final File expectedFile = new File(
				"src/test/resources/io/proleap/cobol/preprocessor/copy/variable/CopyReplace.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}