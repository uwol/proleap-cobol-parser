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

import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.asg.params.impl.CobolParserParamsImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.sub.CobolLine;
import io.proleap.cobol.preprocessor.sub.line.reader.impl.CobolLineReaderImpl;

public class CobolLineReaderTest {

	@Test
	public void testParseCobolLine_FIXED_comment_true() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "000100 Identification Division.                                         1234.6-8";

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setFormat(CobolSourceFormatEnum.FIXED);

		final CobolLine parseCobolLine = preprocessor.parseLine(line, 0, params);

		Assert.assertEquals(CobolSourceFormatEnum.FIXED, parseCobolLine.getFormat());
		Assert.assertEquals("000100", parseCobolLine.getSequenceArea());
		Assert.assertEquals(" ", parseCobolLine.getIndicatorArea());
		Assert.assertEquals("Iden", parseCobolLine.getContentAreaA());
		Assert.assertEquals("tification Division.                                         ",
				parseCobolLine.getContentAreaB());
		Assert.assertEquals("1234.6-8", parseCobolLine.getCommentArea());
	}

	@Test
	public void testParseCobolLine_FIXED_FLEX_false() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "000100 Identification Division.                                        ";

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setFormat(CobolSourceFormatEnum.FIXED);

		final CobolLine parseCobolLine = preprocessor.parseLine(line, 0, params);
		Assert.assertNotNull(parseCobolLine);
	}

	@Test
	public void testParseCobolLine_FIXED_hyph_true() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "000200 Program-ID.                                                      12345678";

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setFormat(CobolSourceFormatEnum.FIXED);

		final CobolLine parseCobolLine = preprocessor.parseLine(line, 0, params);

		Assert.assertEquals(CobolSourceFormatEnum.FIXED, parseCobolLine.getFormat());
		Assert.assertEquals("000200", parseCobolLine.getSequenceArea());
		Assert.assertEquals(" ", parseCobolLine.getIndicatorArea());
		Assert.assertEquals("Prog", parseCobolLine.getContentAreaA());
		Assert.assertEquals("ram-ID.                                                      ",
				parseCobolLine.getContentAreaB());
		Assert.assertEquals("12345678", parseCobolLine.getCommentArea());
	}

	@Test
	public void testParseCobolLine_FIXED_true() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "000100 Identification Division.                                         12345678";

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setFormat(CobolSourceFormatEnum.FIXED);

		final CobolLine parseCobolLine = preprocessor.parseLine(line, 0, params);

		Assert.assertEquals(CobolSourceFormatEnum.FIXED, parseCobolLine.getFormat());
		Assert.assertEquals("000100", parseCobolLine.getSequenceArea());
		Assert.assertEquals(" ", parseCobolLine.getIndicatorArea());
		Assert.assertEquals("Iden", parseCobolLine.getContentAreaA());
		Assert.assertEquals("tification Division.                                         ",
				parseCobolLine.getContentAreaB());
		Assert.assertEquals("12345678", parseCobolLine.getCommentArea());
	}

	@Test
	public void testParseCobolLine_TANDEM_nosequence_false() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "      *HEADER,COBOL,NC114M                                                            ";

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setFormat(CobolSourceFormatEnum.TANDEM);

		final CobolLine parseCobolLine = preprocessor.parseLine(line, 0, params);
		Assert.assertEquals(CobolSourceFormatEnum.TANDEM, parseCobolLine.getFormat());
	}

	@Test(expected = RuntimeException.class)
	public void testParseCobolLine_TANDEM_sequence_false() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "123456 Identification Division.";

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setFormat(CobolSourceFormatEnum.TANDEM);

		final CobolLine parseCobolLine = preprocessor.parseLine(line, 0, params);
		Assert.assertNull(parseCobolLine);
	}

	@Test
	public void testParseCobolLine_TANDEM_trailingWhitespace_true() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = " Procedure Division. ";

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setFormat(CobolSourceFormatEnum.TANDEM);
		final CobolLine parseCobolLine = preprocessor.parseLine(line, 0, params);

		Assert.assertEquals(CobolSourceFormatEnum.TANDEM, parseCobolLine.getFormat());
		Assert.assertEquals("", parseCobolLine.getSequenceArea());
		Assert.assertEquals(" ", parseCobolLine.getIndicatorArea());
		Assert.assertEquals("Proc", parseCobolLine.getContentAreaA());
		Assert.assertEquals("edure Division. ", parseCobolLine.getContentAreaB());
		Assert.assertEquals("", parseCobolLine.getCommentArea());
	}

	@Test
	public void testParseCobolLine_TANDEM_true() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = " Identification Division.";

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setFormat(CobolSourceFormatEnum.TANDEM);
		final CobolLine parseCobolLine = preprocessor.parseLine(line, 0, params);

		Assert.assertEquals(CobolSourceFormatEnum.TANDEM, parseCobolLine.getFormat());
		Assert.assertEquals("", parseCobolLine.getSequenceArea());
		Assert.assertEquals(" ", parseCobolLine.getIndicatorArea());
		Assert.assertEquals("Iden", parseCobolLine.getContentAreaA());
		Assert.assertEquals("tification Division.", parseCobolLine.getContentAreaB());
		Assert.assertEquals("", parseCobolLine.getCommentArea());
	}

	@Test
	public void testParseCobolLine_VARIABLE() throws Exception {
		final CobolLineReader preprocessor = new CobolLineReaderImpl();

		final String line = "000100 Identification Division.";

		final CobolParserParams params = new CobolParserParamsImpl();
		params.setFormat(CobolSourceFormatEnum.VARIABLE);
		final CobolLine parseCobolLine = preprocessor.parseLine(line, 0, params);

		Assert.assertEquals(CobolSourceFormatEnum.VARIABLE, parseCobolLine.getFormat());
		Assert.assertEquals("000100", parseCobolLine.getSequenceArea());
		Assert.assertEquals(" ", parseCobolLine.getIndicatorArea());
		Assert.assertEquals("Iden", parseCobolLine.getContentAreaA());
		Assert.assertEquals("tification Division.", parseCobolLine.getContentAreaB());
		Assert.assertEquals("", parseCobolLine.getCommentArea());
	}
}
