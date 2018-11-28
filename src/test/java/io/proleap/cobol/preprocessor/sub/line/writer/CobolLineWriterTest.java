/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.line.writer;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.sub.CobolLine;
import io.proleap.cobol.preprocessor.sub.CobolLineTypeEnum;
import io.proleap.cobol.preprocessor.sub.line.writer.impl.CobolLineWriterImpl;

public class CobolLineWriterTest {

	@Test
	public void testSerializeLinesLineContinuation() throws Exception {
		final CobolLineWriter writer = new CobolLineWriterImpl();
		final List<CobolLine> lines = new ArrayList<CobolLine>();

		lines.add(CobolLine.newCobolLine("123456", " ", "77  ", "WS-TEST-12-DATA", "NC2054.2",
				CobolSourceFormatEnum.FIXED, null, 0, CobolLineTypeEnum.NORMAL));

		lines.add(CobolLine.newCobolLine("123456", " ", "    ", "                   PIC S9(", "NC2054.2",
				CobolSourceFormatEnum.FIXED, null, 1, CobolLineTypeEnum.NORMAL));

		lines.add(CobolLine.newCobolLine("123456", "-", "6)V9", "(6).", "NC2054.2", CobolSourceFormatEnum.FIXED, null,
				2, CobolLineTypeEnum.CONTINUATION));

		lines.add(CobolLine.newCobolLine("123456", " ", "77  ", "WS-TEST-13-DATA", "NC2054.2",
				CobolSourceFormatEnum.FIXED, null, 3, CobolLineTypeEnum.NORMAL));

		final String serializedInput = writer.serialize(lines);
		final File expectedFile = new File(
				"src/test/resources/io/proleap/cobol/preprocessor/sub/line/writer/LineContinuation.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile, StandardCharsets.UTF_8);
		assertEquals(expected, serializedInput);
	}
}
