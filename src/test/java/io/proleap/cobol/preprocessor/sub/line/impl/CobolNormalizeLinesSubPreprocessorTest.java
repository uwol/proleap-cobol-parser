/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.line.impl;

import org.junit.Assert;
import org.junit.Test;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.sub.impl.CobolLine;
import io.proleap.cobol.preprocessor.sub.normalizer.CobolNormalizeLinesSubPreprocessor;
import io.proleap.cobol.preprocessor.sub.normalizer.impl.CobolNormalizeLinesSubPreprocessorImpl;

public class CobolNormalizeLinesSubPreprocessorTest {

	@Test
	public void testNormalizeLine_VARIABLE() throws Exception {
		final CobolNormalizeLinesSubPreprocessor linesPreprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "123456 ABC .";
		final String normalizeLine = linesPreprocessor.processLines(line, CobolSourceFormatEnum.VARIABLE, null);

		Assert.assertEquals("      " + " " + "ABC .", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation() throws Exception {
		final CobolNormalizeLinesSubPreprocessor linesPreprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "123456-***";
		final String normalizeLine = linesPreprocessor.processLines(line, CobolSourceFormatEnum.VARIABLE, null);

		Assert.assertEquals("***", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation_leadingQuote() throws Exception {
		final CobolNormalizeLinesSubPreprocessor linesPreprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "123456-\"***";
		final String normalizeLine = linesPreprocessor.processLines(line, CobolSourceFormatEnum.VARIABLE, null);

		Assert.assertEquals("***", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation_leadingQuoteAndSpace() throws Exception {
		final CobolNormalizeLinesSubPreprocessor linesPreprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "123456-\"  ***";
		final String normalizeLine = linesPreprocessor.processLines(line, CobolSourceFormatEnum.VARIABLE, null);

		Assert.assertEquals("  ***", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation_leadingSpace() throws Exception {
		final CobolNormalizeLinesSubPreprocessor linesPreprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "123456-  ***";
		final String normalizeLine = linesPreprocessor.processLines(line, CobolSourceFormatEnum.VARIABLE, null);

		Assert.assertEquals("***", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_leadingSpace() throws Exception {
		final CobolNormalizeLinesSubPreprocessor linesPreprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "123456   ABC .";
		final String normalizeLine = linesPreprocessor.processLines(line, CobolSourceFormatEnum.VARIABLE, null);

		Assert.assertEquals("      " + " " + "  ABC .", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_trailingComma() throws Exception {
		final CobolNormalizeLinesSubPreprocessor linesPreprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "123456 ABC ,";
		final String normalizeLine = linesPreprocessor.processLines(line, CobolSourceFormatEnum.VARIABLE, null);

		Assert.assertEquals("      " + " " + "ABC , ", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_trailingSemicolon() throws Exception {
		final CobolNormalizeLinesSubPreprocessor linesPreprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "123456 ABC ;";
		final String normalizeLine = linesPreprocessor.processLines(line, CobolSourceFormatEnum.VARIABLE, null);

		Assert.assertEquals("      " + " " + "ABC ; ", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_trailingWhitspace() throws Exception {
		final CobolNormalizeLinesSubPreprocessor linesPreprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "123456 ABC .  ";
		final String normalizeLine = linesPreprocessor.processLines(line, CobolSourceFormatEnum.VARIABLE, null);

		Assert.assertEquals("      " + " " + "ABC .", normalizeLine);
	}

	@Test
	public void testParseCobol85Line_FIXED_comment_true() throws Exception {
		final CobolNormalizeLinesSubPreprocessor preprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "000100 Identification Division.                                         1234.6-8";
		final CobolLine parseCobol85Line = preprocessor.parseCobolLine(line, CobolSourceFormatEnum.FIXED);

		Assert.assertEquals(CobolSourceFormatEnum.FIXED, parseCobol85Line.lineFormat);
		Assert.assertEquals("000100", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Iden", parseCobol85Line.contentAreaA);
		Assert.assertEquals("tification Division.                                         ",
				parseCobol85Line.contentAreaB);
		Assert.assertEquals("1234.6-8", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_FIXED_FLEX_false() throws Exception {
		final CobolNormalizeLinesSubPreprocessor preprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "000100 Identification Division.                                        ";
		final CobolLine parseCobol85Line = preprocessor.parseCobolLine(line, CobolSourceFormatEnum.FIXED);

		Assert.assertNotNull(parseCobol85Line);
	}

	@Test
	public void testParseCobol85Line_FIXED_hyph_true() throws Exception {
		final CobolNormalizeLinesSubPreprocessor preprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "000200 Program-ID.                                                      12345678";
		final CobolLine parseCobol85Line = preprocessor.parseCobolLine(line, CobolSourceFormatEnum.FIXED);

		Assert.assertEquals(CobolSourceFormatEnum.FIXED, parseCobol85Line.lineFormat);
		Assert.assertEquals("000200", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Prog", parseCobol85Line.contentAreaA);
		Assert.assertEquals("ram-ID.                                                      ",
				parseCobol85Line.contentAreaB);
		Assert.assertEquals("12345678", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_FIXED_true() throws Exception {
		final CobolNormalizeLinesSubPreprocessor preprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "000100 Identification Division.                                         12345678";
		final CobolLine parseCobol85Line = preprocessor.parseCobolLine(line, CobolSourceFormatEnum.FIXED);

		Assert.assertEquals(CobolSourceFormatEnum.FIXED, parseCobol85Line.lineFormat);
		Assert.assertEquals("000100", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Iden", parseCobol85Line.contentAreaA);
		Assert.assertEquals("tification Division.                                         ",
				parseCobol85Line.contentAreaB);
		Assert.assertEquals("12345678", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_TANDEM_nosequence_false() throws Exception {
		final CobolNormalizeLinesSubPreprocessor preprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "      *HEADER,COBOL,NC114M                                                            ";
		final CobolLine parseCobol85Line = preprocessor.parseCobolLine(line, CobolSourceFormatEnum.TANDEM);

		Assert.assertEquals(CobolSourceFormatEnum.TANDEM, parseCobol85Line.lineFormat);
	}

	@Test
	public void testParseCobol85Line_TANDEM_sequence_false() throws Exception {
		final CobolNormalizeLinesSubPreprocessor preprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "123456 Identification Division.";
		final CobolLine parseCobol85Line = preprocessor.parseCobolLine(line, CobolSourceFormatEnum.TANDEM);

		Assert.assertNull(parseCobol85Line);
	}

	@Test
	public void testParseCobol85Line_TANDEM_trailingWhitespace_true() throws Exception {
		final CobolNormalizeLinesSubPreprocessor preprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = " Procedure Division. ";
		final CobolLine parseCobol85Line = preprocessor.parseCobolLine(line, CobolSourceFormatEnum.TANDEM);

		Assert.assertEquals(CobolSourceFormatEnum.TANDEM, parseCobol85Line.lineFormat);
		Assert.assertEquals("", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Proc", parseCobol85Line.contentAreaA);
		Assert.assertEquals("edure Division. ", parseCobol85Line.contentAreaB);
		Assert.assertEquals("", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_TANDEM_true() throws Exception {
		final CobolNormalizeLinesSubPreprocessor preprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = " Identification Division.";
		final CobolLine parseCobol85Line = preprocessor.parseCobolLine(line, CobolSourceFormatEnum.TANDEM);

		Assert.assertEquals(CobolSourceFormatEnum.TANDEM, parseCobol85Line.lineFormat);
		Assert.assertEquals("", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Iden", parseCobol85Line.contentAreaA);
		Assert.assertEquals("tification Division.", parseCobol85Line.contentAreaB);
		Assert.assertEquals("", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_VARIABLE() throws Exception {
		final CobolNormalizeLinesSubPreprocessor preprocessor = new CobolNormalizeLinesSubPreprocessorImpl();

		final String line = "000100 Identification Division.";
		final CobolLine parseCobol85Line = preprocessor.parseCobolLine(line, CobolSourceFormatEnum.VARIABLE);

		Assert.assertEquals(CobolSourceFormatEnum.VARIABLE, parseCobol85Line.lineFormat);
		Assert.assertEquals("000100", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Iden", parseCobol85Line.contentAreaA);
		Assert.assertEquals("tification Division.", parseCobol85Line.contentAreaB);
		Assert.assertEquals("", parseCobol85Line.comment);
	}
}
