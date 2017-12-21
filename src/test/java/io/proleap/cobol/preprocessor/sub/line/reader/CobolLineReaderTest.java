/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.line.reader;

import org.junit.Assert;
import org.junit.Test;

import io.proleap.cobol.asg.params.impl.CobolParserParamsImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.sub.CobolLine;
import io.proleap.cobol.preprocessor.sub.line.reader.impl.CobolLineReaderImpl;

public class CobolLineReaderTest {

	@Test
	public void testParseCobol85Line_FIXED_comment_true() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "000100 Identification Division.                                         1234.6-8";
		final CobolLine parseCobol85Line = preprocessor.parseLine(line, 0, CobolSourceFormatEnum.FIXED,
				new CobolParserParamsImpl());

		Assert.assertEquals(CobolSourceFormatEnum.FIXED, parseCobol85Line.getFormat());
		Assert.assertEquals("000100", parseCobol85Line.getSequenceArea());
		Assert.assertEquals(" ", parseCobol85Line.getIndicatorArea());
		Assert.assertEquals("Iden", parseCobol85Line.getContentAreaA());
		Assert.assertEquals("tification Division.                                         ",
				parseCobol85Line.getContentAreaB());
		Assert.assertEquals("1234.6-8", parseCobol85Line.getCommentArea());
	}

	@Test
	public void testParseCobol85Line_FIXED_FLEX_false() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "000100 Identification Division.                                        ";
		final CobolLine parseCobol85Line = preprocessor.parseLine(line, 0, CobolSourceFormatEnum.FIXED,
				new CobolParserParamsImpl());

		Assert.assertNotNull(parseCobol85Line);
	}

	@Test
	public void testParseCobol85Line_FIXED_hyph_true() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "000200 Program-ID.                                                      12345678";
		final CobolLine parseCobol85Line = preprocessor.parseLine(line, 0, CobolSourceFormatEnum.FIXED,
				new CobolParserParamsImpl());

		Assert.assertEquals(CobolSourceFormatEnum.FIXED, parseCobol85Line.getFormat());
		Assert.assertEquals("000200", parseCobol85Line.getSequenceArea());
		Assert.assertEquals(" ", parseCobol85Line.getIndicatorArea());
		Assert.assertEquals("Prog", parseCobol85Line.getContentAreaA());
		Assert.assertEquals("ram-ID.                                                      ",
				parseCobol85Line.getContentAreaB());
		Assert.assertEquals("12345678", parseCobol85Line.getCommentArea());
	}

	@Test
	public void testParseCobol85Line_FIXED_true() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "000100 Identification Division.                                         12345678";
		final CobolLine parseCobol85Line = preprocessor.parseLine(line, 0, CobolSourceFormatEnum.FIXED,
				new CobolParserParamsImpl());

		Assert.assertEquals(CobolSourceFormatEnum.FIXED, parseCobol85Line.getFormat());
		Assert.assertEquals("000100", parseCobol85Line.getSequenceArea());
		Assert.assertEquals(" ", parseCobol85Line.getIndicatorArea());
		Assert.assertEquals("Iden", parseCobol85Line.getContentAreaA());
		Assert.assertEquals("tification Division.                                         ",
				parseCobol85Line.getContentAreaB());
		Assert.assertEquals("12345678", parseCobol85Line.getCommentArea());
	}

	@Test
	public void testParseCobol85Line_TANDEM_nosequence_false() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "      *HEADER,COBOL,NC114M                                                            ";
		final CobolLine parseCobol85Line = preprocessor.parseLine(line, 0, CobolSourceFormatEnum.TANDEM,
				new CobolParserParamsImpl());

		Assert.assertEquals(CobolSourceFormatEnum.TANDEM, parseCobol85Line.getFormat());
	}

	@Test(expected = RuntimeException.class)
	public void testParseCobol85Line_TANDEM_sequence_false() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "123456 Identification Division.";
		final CobolLine parseCobol85Line = preprocessor.parseLine(line, 0, CobolSourceFormatEnum.TANDEM,
				new CobolParserParamsImpl());

		Assert.assertNull(parseCobol85Line);
	}

	@Test
	public void testParseCobol85Line_TANDEM_trailingWhitespace_true() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = " Procedure Division. ";
		final CobolLine parseCobol85Line = preprocessor.parseLine(line, 0, CobolSourceFormatEnum.TANDEM,
				new CobolParserParamsImpl());

		Assert.assertEquals(CobolSourceFormatEnum.TANDEM, parseCobol85Line.getFormat());
		Assert.assertEquals("", parseCobol85Line.getSequenceArea());
		Assert.assertEquals(" ", parseCobol85Line.getIndicatorArea());
		Assert.assertEquals("Proc", parseCobol85Line.getContentAreaA());
		Assert.assertEquals("edure Division. ", parseCobol85Line.getContentAreaB());
		Assert.assertEquals("", parseCobol85Line.getCommentArea());
	}

	@Test
	public void testParseCobol85Line_TANDEM_true() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = " Identification Division.";
		final CobolLine parseCobol85Line = preprocessor.parseLine(line, 0, CobolSourceFormatEnum.TANDEM,
				new CobolParserParamsImpl());

		Assert.assertEquals(CobolSourceFormatEnum.TANDEM, parseCobol85Line.getFormat());
		Assert.assertEquals("", parseCobol85Line.getSequenceArea());
		Assert.assertEquals(" ", parseCobol85Line.getIndicatorArea());
		Assert.assertEquals("Iden", parseCobol85Line.getContentAreaA());
		Assert.assertEquals("tification Division.", parseCobol85Line.getContentAreaB());
		Assert.assertEquals("", parseCobol85Line.getCommentArea());
	}

	@Test
	public void testParseCobol85Line_VARIABLE() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "000100 Identification Division.";
		final CobolLine parseCobol85Line = preprocessor.parseLine(line, 0, CobolSourceFormatEnum.VARIABLE,
				new CobolParserParamsImpl());

		Assert.assertEquals(CobolSourceFormatEnum.VARIABLE, parseCobol85Line.getFormat());
		Assert.assertEquals("000100", parseCobol85Line.getSequenceArea());
		Assert.assertEquals(" ", parseCobol85Line.getIndicatorArea());
		Assert.assertEquals("Iden", parseCobol85Line.getContentAreaA());
		Assert.assertEquals("tification Division.", parseCobol85Line.getContentAreaB());
		Assert.assertEquals("", parseCobol85Line.getCommentArea());
	}
}
