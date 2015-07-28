package org.cobol85.preprocessor;

import org.cobol85.preprocessor.Cobol85Preprocessor.Cobol85Format;
import org.cobol85.preprocessor.impl.Cobol85PreprocessorImpl;
import org.junit.Assert;
import org.junit.Test;

public class Cobol85PreprocessorTest {

	private final Cobol85Preprocessor preprocessor = new Cobol85PreprocessorImpl();

	@Test
	public void testHasLineFormat_FIXED_comment_true() throws Exception {
		final String line = "000100 Identification Division.                                         1234.6-8";
		Assert.assertTrue(preprocessor.hasLineFormat(line, Cobol85Format.FIXED));
	}

	@Test
	public void testHasLineFormat_FIXED_false() throws Exception {
		final String line = "000100 Identification Division.                                        ";
		Assert.assertFalse(preprocessor.hasLineFormat(line, Cobol85Format.FIXED));
	}

	@Test
	public void testHasLineFormat_FIXED_hyph_true() throws Exception {
		final String line = "000200 Program-ID.                                                      12345678";
		Assert.assertTrue(preprocessor.hasLineFormat(line, Cobol85Format.FIXED));
	}

	@Test
	public void testHasLineFormat_FIXED_true() throws Exception {
		final String line = "000100 Identification Division.                                         12345678";
		Assert.assertTrue(preprocessor.hasLineFormat(line, Cobol85Format.FIXED));
	}

	@Test
	public void testHasLineFormat_TANDEM_false() throws Exception {
		final String line = "123456 Identification Division.";
		Assert.assertFalse(preprocessor.hasLineFormat(line, Cobol85Format.TANDEM));
	}

	@Test
	public void testHasLineFormat_TANDEM_true() throws Exception {
		final String line = " Identification Division.";
		Assert.assertTrue(preprocessor.hasLineFormat(line, Cobol85Format.TANDEM));
	}

	@Test
	public void testHasLineFormat_VARIABLE() throws Exception {
		final String line = "000100 Identification Division.";
		Assert.assertTrue(preprocessor.hasLineFormat(line, Cobol85Format.VARIABLE));
	}
}
