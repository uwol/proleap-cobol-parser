package org.cobol85.preprocessor;

import org.cobol85.preprocessor.Cobol85Preprocessor.Cobol85Format;
import org.cobol85.preprocessor.Cobol85Preprocessor.Cobol85Line;
import org.cobol85.preprocessor.impl.Cobol85PreprocessorImpl;
import org.junit.Assert;
import org.junit.Test;

public class Cobol85PreprocessorTest {

	private final Cobol85Preprocessor preprocessor = new Cobol85PreprocessorImpl();

	@Test
	public void testNormalizeLine_VARIABLE() throws Exception {
		final Cobol85Line line = new Cobol85Line("123456", ' ', "ABC .", "", Cobol85Format.VARIABLE);
		final String normalizeLine = preprocessor.normalizeLine(line, true);

		Assert.assertEquals("      " + " " + "ABC .", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation() throws Exception {
		final Cobol85Line line = new Cobol85Line("123456", '-', "***", "", Cobol85Format.VARIABLE);
		final String normalizeLine = preprocessor.normalizeLine(line, true);

		Assert.assertEquals("***", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation_leadingQuote() throws Exception {
		final Cobol85Line line = new Cobol85Line("123456", '-', "\"***", "", Cobol85Format.VARIABLE);
		final String normalizeLine = preprocessor.normalizeLine(line, true);

		Assert.assertEquals("***", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation_leadingQuoteAndSpace() throws Exception {
		final Cobol85Line line = new Cobol85Line("123456", '-', "\"  ***", "", Cobol85Format.VARIABLE);
		final String normalizeLine = preprocessor.normalizeLine(line, true);

		Assert.assertEquals("  ***", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation_leadingSpace() throws Exception {
		final Cobol85Line line = new Cobol85Line("123456", '-', "  ***", "", Cobol85Format.VARIABLE);
		final String normalizeLine = preprocessor.normalizeLine(line, true);

		Assert.assertEquals("***", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_leadingSpace() throws Exception {
		final Cobol85Line line = new Cobol85Line("123456", ' ', "  ABC .", "", Cobol85Format.VARIABLE);
		final String normalizeLine = preprocessor.normalizeLine(line, true);

		Assert.assertEquals("      " + " " + "  ABC .", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_trailingComma() throws Exception {
		final Cobol85Line line = new Cobol85Line("123456", ' ', "ABC ,", "", Cobol85Format.VARIABLE);
		final String normalizeLine = preprocessor.normalizeLine(line, true);

		Assert.assertEquals("      " + " " + "ABC , ", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_trailingSemicolon() throws Exception {
		final Cobol85Line line = new Cobol85Line("123456", ' ', "ABC ;", "", Cobol85Format.VARIABLE);
		final String normalizeLine = preprocessor.normalizeLine(line, true);

		Assert.assertEquals("      " + " " + "ABC ; ", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_trailingWhitspace() throws Exception {
		final Cobol85Line line = new Cobol85Line("123456", ' ', "ABC .  ", "", Cobol85Format.VARIABLE);
		final String normalizeLine = preprocessor.normalizeLine(line, true);

		Assert.assertEquals("      " + " " + "ABC .", normalizeLine);
	}

	@Test
	public void testParseCobol85Line_FIXED_comment_true() throws Exception {
		final String line = "000100 Identification Division.                                         1234.6-8";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line,
				new Cobol85Format[] { Cobol85Format.FIXED });

		Assert.assertEquals(Cobol85Format.FIXED, parseCobol85Line.lineFormat);
		Assert.assertEquals("000100", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Identification Division.                                         ",
				parseCobol85Line.contentArea);
		Assert.assertEquals("1234.6-8", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_FIXED_false() throws Exception {
		final String line = "000100 Identification Division.                                        ";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line,
				new Cobol85Format[] { Cobol85Format.FIXED });

		Assert.assertNull(parseCobol85Line);
	}

	@Test
	public void testParseCobol85Line_FIXED_hyph_true() throws Exception {
		final String line = "000200 Program-ID.                                                      12345678";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line,
				new Cobol85Format[] { Cobol85Format.FIXED });

		Assert.assertEquals(Cobol85Format.FIXED, parseCobol85Line.lineFormat);
		Assert.assertEquals("000200", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Program-ID.                                                      ",
				parseCobol85Line.contentArea);
		Assert.assertEquals("12345678", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_FIXED_true() throws Exception {
		final String line = "000100 Identification Division.                                         12345678";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line,
				new Cobol85Format[] { Cobol85Format.FIXED });

		Assert.assertEquals(Cobol85Format.FIXED, parseCobol85Line.lineFormat);
		Assert.assertEquals("000100", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Identification Division.                                         ",
				parseCobol85Line.contentArea);
		Assert.assertEquals("12345678", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_TANDEM_nosequence_false() throws Exception {
		final String line = "      *HEADER,COBOL,NC114M                                                            ";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line,
				new Cobol85Format[] { Cobol85Format.TANDEM });

		Assert.assertEquals(Cobol85Format.TANDEM, parseCobol85Line.lineFormat);
	}

	@Test
	public void testParseCobol85Line_TANDEM_sequence_false() throws Exception {
		final String line = "123456 Identification Division.";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line,
				new Cobol85Format[] { Cobol85Format.TANDEM });

		Assert.assertNull(parseCobol85Line);
	}

	@Test
	public void testParseCobol85Line_TANDEM_trailingWhitespace_true() throws Exception {
		final String line = " Procedure Division. ";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line,
				new Cobol85Format[] { Cobol85Format.TANDEM });

		Assert.assertEquals(Cobol85Format.TANDEM, parseCobol85Line.lineFormat);
		Assert.assertEquals("", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Procedure Division. ", parseCobol85Line.contentArea);
		Assert.assertEquals("", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_TANDEM_true() throws Exception {
		final String line = " Identification Division.";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line,
				new Cobol85Format[] { Cobol85Format.TANDEM });

		Assert.assertEquals(Cobol85Format.TANDEM, parseCobol85Line.lineFormat);
		Assert.assertEquals("", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Identification Division.", parseCobol85Line.contentArea);
		Assert.assertEquals("", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_VARIABLE() throws Exception {
		final String line = "000100 Identification Division.";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line,
				new Cobol85Format[] { Cobol85Format.VARIABLE });

		Assert.assertEquals(Cobol85Format.VARIABLE, parseCobol85Line.lineFormat);
		Assert.assertEquals("000100", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Identification Division.", parseCobol85Line.contentArea);
		Assert.assertEquals("", parseCobol85Line.comment);
	}
}
