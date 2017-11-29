package io.proleap.cobol.preprocessor.copy.linkage.variable;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;

public class CopyLinkage {

	private static final String DIR = "src/test/resources/io/proleap/cobol/preprocessor/copy/linkage/variable";

	@Test
	public void testCopyBooks() throws Exception {
		final File inputFile = new File(DIR + "/CopyLinkage.cbl");
		final File copyFile = new File(DIR + "/copybooks/Linkage");
		final ArrayList<File> copyFiles = Lists.newArrayList(copyFile);

		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, copyFiles,
				CobolSourceFormatEnum.VARIABLE);

		final File expectedFile = new File(DIR + "/CopyLinkage.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}

	@Test
	public void testCopyDirs() throws Exception {
		final File inputFile = new File(DIR + "/CopyLinkage.cbl");
		final File copyDir = new File(DIR + "/copybooks");
		final ArrayList<File> copyDirs = Lists.newArrayList(copyDir);

		final String preProcessedInput = new CobolPreprocessorImpl().process(inputFile, copyDirs,
				CobolSourceFormatEnum.VARIABLE);

		final File expectedFile = new File(DIR + "/CopyLinkage.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}