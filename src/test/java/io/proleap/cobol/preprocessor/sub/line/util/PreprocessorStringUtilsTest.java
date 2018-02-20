/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.line.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.proleap.cobol.preprocessor.sub.util.PreprocessorStringUtils;

public class PreprocessorStringUtilsTest {

	@Test
	public void testTrimQuotesDoubleQuote() throws Exception {
		final String input = "\"test\"";
		final String trimQuotes = PreprocessorStringUtils.trimQuotes(input);

		assertEquals("test", trimQuotes);
	}

	@Test
	public void testTrimQuotesSingleQuote() throws Exception {
		final String input = "'test'";
		final String trimQuotes = PreprocessorStringUtils.trimQuotes(input);

		assertEquals("test", trimQuotes);
	}
}
