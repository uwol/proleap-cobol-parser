/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.line.rewriter;

import static io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum.VARIABLE;
import static io.proleap.cobol.preprocessor.sub.CobolLineTypeEnum.CONTINUATION;
import static io.proleap.cobol.preprocessor.sub.CobolLineTypeEnum.NORMAL;

import org.junit.Assert;
import org.junit.Test;

import io.proleap.cobol.preprocessor.sub.CobolLine;
import io.proleap.cobol.preprocessor.sub.line.rewriter.impl.CobolLineIndicatorProcessorImpl;

public class CobolLineIndicatorProcessorTest {

	@Test
	public void testNormalizeLine_VARIABLE() throws Exception {
		final CobolLineIndicatorProcessor processor = new CobolLineIndicatorProcessorImpl();

		final CobolLine line = CobolLine.newCobolLine("123456", " ", "ABC ", ".", "", VARIABLE, null, 0, NORMAL);
		final CobolLine normalizedLine = processor.processLine(line);

		Assert.assertEquals("123456" + " " + "ABC .", normalizedLine.serialize());
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation() throws Exception {
		final CobolLineIndicatorProcessor processor = new CobolLineIndicatorProcessorImpl();

		final CobolLine line = CobolLine.newCobolLine("123456", "-", "***", "", "", VARIABLE, null, 0, CONTINUATION);
		final CobolLine normalizedLine = processor.processLine(line);

		Assert.assertEquals("***", normalizedLine.getContentArea());
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation_leadingQuote() throws Exception {
		final CobolLineIndicatorProcessor processor = new CobolLineIndicatorProcessorImpl();

		final CobolLine line = CobolLine.newCobolLine("123456", "-", "\"***", "", "", VARIABLE, null, 0, CONTINUATION);
		final CobolLine normalizeLine = processor.processLine(line);

		Assert.assertEquals("\"***", normalizeLine.getContentArea());
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation_leadingQuoteAndSpace() throws Exception {
		final CobolLineIndicatorProcessor processor = new CobolLineIndicatorProcessorImpl();

		final CobolLine line = CobolLine.newCobolLine("123456", "-", "\"  *", "**", "", VARIABLE, null, 0,
				CONTINUATION);
		final CobolLine normalizedLine = processor.processLine(line);

		Assert.assertEquals("\"  ***", normalizedLine.getContentArea());
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation_leadingSpace() throws Exception {
		final CobolLineIndicatorProcessor processor = new CobolLineIndicatorProcessorImpl();

		final CobolLine line = CobolLine.newCobolLine("123456", "-", "  **", "*", "", VARIABLE, null, 0, CONTINUATION);
		final CobolLine normalizedLine = processor.processLine(line);

		Assert.assertEquals("***", normalizedLine.getContentArea());
	}

	@Test
	public void testNormalizeLine_VARIABLE_leadingSpace() throws Exception {
		final CobolLineIndicatorProcessor processor = new CobolLineIndicatorProcessorImpl();

		final CobolLine line = CobolLine.newCobolLine("123456", " ", "  AB", "C .", "", VARIABLE, null, 0, NORMAL);
		final CobolLine normalizedLine = processor.processLine(line);

		Assert.assertEquals("123456" + " " + "  ABC .", normalizedLine.serialize());
	}

	@Test
	public void testNormalizeLine_VARIABLE_trailingComma() throws Exception {
		final CobolLineIndicatorProcessor processor = new CobolLineIndicatorProcessorImpl();

		final CobolLine line = CobolLine.newCobolLine("123456", " ", "ABC ", ",", "", VARIABLE, null, 0, NORMAL);
		final CobolLine normalizedLine = processor.processLine(line);

		Assert.assertEquals("123456" + " " + "ABC , ", normalizedLine.serialize());
	}

	@Test
	public void testNormalizeLine_VARIABLE_trailingSemicolon() throws Exception {
		final CobolLineIndicatorProcessor processor = new CobolLineIndicatorProcessorImpl();

		final CobolLine line = CobolLine.newCobolLine("123456", " ", "ABC ", ";", "", VARIABLE, null, 0, NORMAL);
		final CobolLine normalizedLine = processor.processLine(line);

		Assert.assertEquals("123456" + " " + "ABC ; ", normalizedLine.serialize());
	}

	@Test
	public void testNormalizeLine_VARIABLE_trailingWhitspace() throws Exception {
		final CobolLineIndicatorProcessor processor = new CobolLineIndicatorProcessorImpl();

		final CobolLine line = CobolLine.newCobolLine("123456", " ", "ABC ", ".  ", "", VARIABLE, null, 0, NORMAL);
		final CobolLine normalizedLine = processor.processLine(line);

		Assert.assertEquals("123456" + " " + "ABC .", normalizedLine.serialize());
	}
}
