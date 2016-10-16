package io.proleap.cobol.preprocessor.impl;

import org.junit.Assert;
import org.junit.Test;

import io.proleap.cobol.preprocessor.Cobol85Preprocessor.Cobol85Line;
import io.proleap.cobol.preprocessor.Cobol85Preprocessor.Cobol85SourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.Cobol85PreprocessorImpl;
import io.proleap.cobol.preprocessor.impl.Cobol85PreprocessorImpl.AbstractCobol85LinesSubPreprocessor;

public class Cobol85NormalizerPreprocessorImplTest {

	@Test
	public void testNormalizeLine_VARIABLE() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();
		final AbstractCobol85LinesSubPreprocessor linesPreprocessor = preprocessor.new Cobol85NormalizeLinesSubPreprocessorImpl();

		final String line = "123456 ABC .";

		final String normalizeLine = linesPreprocessor.processLines(line, null, Cobol85SourceFormatEnum.VARIABLE);

		Assert.assertEquals("      " + " " + "ABC .", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();
		final AbstractCobol85LinesSubPreprocessor linesPreprocessor = preprocessor.new Cobol85NormalizeLinesSubPreprocessorImpl();

		final String line = "123456-***";
		final String normalizeLine = linesPreprocessor.processLines(line, null, Cobol85SourceFormatEnum.VARIABLE);

		Assert.assertEquals("***", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation_leadingQuote() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();
		final AbstractCobol85LinesSubPreprocessor linesPreprocessor = preprocessor.new Cobol85NormalizeLinesSubPreprocessorImpl();

		final String line = "123456-\"***";
		final String normalizeLine = linesPreprocessor.processLines(line, null, Cobol85SourceFormatEnum.VARIABLE);

		Assert.assertEquals("***", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation_leadingQuoteAndSpace() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();
		final AbstractCobol85LinesSubPreprocessor linesPreprocessor = preprocessor.new Cobol85NormalizeLinesSubPreprocessorImpl();

		final String line = "123456-\"  ***";
		final String normalizeLine = linesPreprocessor.processLines(line, null, Cobol85SourceFormatEnum.VARIABLE);

		Assert.assertEquals("  ***", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_continuation_leadingSpace() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();
		final AbstractCobol85LinesSubPreprocessor linesPreprocessor = preprocessor.new Cobol85NormalizeLinesSubPreprocessorImpl();

		final String line = "123456-  ***";
		final String normalizeLine = linesPreprocessor.processLines(line, null, Cobol85SourceFormatEnum.VARIABLE);

		Assert.assertEquals("***", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_leadingSpace() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();
		final AbstractCobol85LinesSubPreprocessor linesPreprocessor = preprocessor.new Cobol85NormalizeLinesSubPreprocessorImpl();

		final String line = "123456   ABC .";
		final String normalizeLine = linesPreprocessor.processLines(line, null, Cobol85SourceFormatEnum.VARIABLE);

		Assert.assertEquals("      " + " " + "  ABC .", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_trailingComma() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();
		final AbstractCobol85LinesSubPreprocessor linesPreprocessor = preprocessor.new Cobol85NormalizeLinesSubPreprocessorImpl();

		final String line = "123456 ABC ,";
		final String normalizeLine = linesPreprocessor.processLines(line, null, Cobol85SourceFormatEnum.VARIABLE);

		Assert.assertEquals("      " + " " + "ABC , ", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_trailingSemicolon() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();
		final AbstractCobol85LinesSubPreprocessor linesPreprocessor = preprocessor.new Cobol85NormalizeLinesSubPreprocessorImpl();

		final String line = "123456 ABC ;";
		final String normalizeLine = linesPreprocessor.processLines(line, null, Cobol85SourceFormatEnum.VARIABLE);

		Assert.assertEquals("      " + " " + "ABC ; ", normalizeLine);
	}

	@Test
	public void testNormalizeLine_VARIABLE_trailingWhitspace() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();
		final AbstractCobol85LinesSubPreprocessor linesPreprocessor = preprocessor.new Cobol85NormalizeLinesSubPreprocessorImpl();

		final String line = "123456 ABC .  ";
		final String normalizeLine = linesPreprocessor.processLines(line, null, Cobol85SourceFormatEnum.VARIABLE);

		Assert.assertEquals("      " + " " + "ABC .", normalizeLine);
	}

	@Test
	public void testParseCobol85Line_FIXED_comment_true() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();

		final String line = "000100 Identification Division.                                         1234.6-8";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line, Cobol85SourceFormatEnum.FIXED);

		Assert.assertEquals(Cobol85SourceFormatEnum.FIXED, parseCobol85Line.lineFormat);
		Assert.assertEquals("000100", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Iden", parseCobol85Line.contentAreaA);
		Assert.assertEquals("tification Division.                                         ",
				parseCobol85Line.contentAreaB);
		Assert.assertEquals("1234.6-8", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_FIXED_false() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();

		final String line = "000100 Identification Division.                                        ";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line, Cobol85SourceFormatEnum.FIXED);

		Assert.assertNull(parseCobol85Line);
	}

	@Test
	public void testParseCobol85Line_FIXED_hyph_true() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();

		final String line = "000200 Program-ID.                                                      12345678";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line, Cobol85SourceFormatEnum.FIXED);

		Assert.assertEquals(Cobol85SourceFormatEnum.FIXED, parseCobol85Line.lineFormat);
		Assert.assertEquals("000200", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Prog", parseCobol85Line.contentAreaA);
		Assert.assertEquals("ram-ID.                                                      ",
				parseCobol85Line.contentAreaB);
		Assert.assertEquals("12345678", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_FIXED_true() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();

		final String line = "000100 Identification Division.                                         12345678";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line, Cobol85SourceFormatEnum.FIXED);

		Assert.assertEquals(Cobol85SourceFormatEnum.FIXED, parseCobol85Line.lineFormat);
		Assert.assertEquals("000100", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Iden", parseCobol85Line.contentAreaA);
		Assert.assertEquals("tification Division.                                         ",
				parseCobol85Line.contentAreaB);
		Assert.assertEquals("12345678", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_TANDEM_nosequence_false() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();

		final String line = "      *HEADER,COBOL,NC114M                                                            ";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line, Cobol85SourceFormatEnum.TANDEM);

		Assert.assertEquals(Cobol85SourceFormatEnum.TANDEM, parseCobol85Line.lineFormat);
	}

	@Test
	public void testParseCobol85Line_TANDEM_sequence_false() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();

		final String line = "123456 Identification Division.";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line, Cobol85SourceFormatEnum.TANDEM);

		Assert.assertNull(parseCobol85Line);
	}

	@Test
	public void testParseCobol85Line_TANDEM_trailingWhitespace_true() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();

		final String line = " Procedure Division. ";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line, Cobol85SourceFormatEnum.TANDEM);

		Assert.assertEquals(Cobol85SourceFormatEnum.TANDEM, parseCobol85Line.lineFormat);
		Assert.assertEquals("", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Proc", parseCobol85Line.contentAreaA);
		Assert.assertEquals("edure Division. ", parseCobol85Line.contentAreaB);
		Assert.assertEquals("", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_TANDEM_true() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();

		final String line = " Identification Division.";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line, Cobol85SourceFormatEnum.TANDEM);

		Assert.assertEquals(Cobol85SourceFormatEnum.TANDEM, parseCobol85Line.lineFormat);
		Assert.assertEquals("", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Iden", parseCobol85Line.contentAreaA);
		Assert.assertEquals("tification Division.", parseCobol85Line.contentAreaB);
		Assert.assertEquals("", parseCobol85Line.comment);
	}

	@Test
	public void testParseCobol85Line_VARIABLE() throws Exception {
		final Cobol85PreprocessorImpl preprocessor = new Cobol85PreprocessorImpl();

		final String line = "000100 Identification Division.";
		final Cobol85Line parseCobol85Line = preprocessor.parseCobol85Line(line, Cobol85SourceFormatEnum.VARIABLE);

		Assert.assertEquals(Cobol85SourceFormatEnum.VARIABLE, parseCobol85Line.lineFormat);
		Assert.assertEquals("000100", parseCobol85Line.sequenceArea);
		Assert.assertEquals(' ', parseCobol85Line.indicatorArea);
		Assert.assertEquals("Iden", parseCobol85Line.contentAreaA);
		Assert.assertEquals("tification Division.", parseCobol85Line.contentAreaB);
		Assert.assertEquals("", parseCobol85Line.comment);
	}
}
