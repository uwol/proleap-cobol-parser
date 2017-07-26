/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.line.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.proleap.cobol.preprocessor.sub.util.StringUtils;

public class StringUtilsTest {

	@Test
	public void testTrimQuotesDoubleQuote() throws Exception {
		final String input = "\"test\"";
		final String trimQuotes = StringUtils.trimQuotes(input);

		assertEquals("test", trimQuotes);
	}

	@Test
	public void testTrimQuotesSingleQuote() throws Exception {
		final String input = "'test'";
		final String trimQuotes = StringUtils.trimQuotes(input);

		assertEquals("test", trimQuotes);
	}
}
