/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.util;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Strings;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CobolSourceFormatUtilsTest {

	private static final String WS = " ";

	@Test
	public void testGetBlankLineFixed() throws Exception {
		final CobolSourceFormatEnum format = CobolSourceFormatEnum.FIXED;
		Assert.assertEquals(Strings.repeat(WS, 80), CobolSourceFormatUtils.getBlankLine(format));
	}

	@Test
	public void testGetBlankLineTandem() throws Exception {
		final CobolSourceFormatEnum format = CobolSourceFormatEnum.TANDEM;
		Assert.assertEquals(WS, CobolSourceFormatUtils.getBlankLine(format));
	}

	@Test
	public void testGetBlankLineVariable() throws Exception {
		final CobolSourceFormatEnum format = CobolSourceFormatEnum.VARIABLE;
		Assert.assertEquals(Strings.repeat(WS, 7), CobolSourceFormatUtils.getBlankLine(format));
	}

	@Test
	public void testGetBlankSequenceAreaFixed() throws Exception {
		final CobolSourceFormatEnum format = CobolSourceFormatEnum.FIXED;
		Assert.assertEquals(Strings.repeat(WS, 6), CobolSourceFormatUtils.getBlankSequenceArea(format));
	}

	@Test
	public void testGetBlankSequenceAreaTandem() throws Exception {
		final CobolSourceFormatEnum format = CobolSourceFormatEnum.TANDEM;
		Assert.assertEquals("", CobolSourceFormatUtils.getBlankSequenceArea(format));
	}

	@Test
	public void testGetBlankSequenceAreaVariable() throws Exception {
		final CobolSourceFormatEnum format = CobolSourceFormatEnum.VARIABLE;
		Assert.assertEquals(Strings.repeat(WS, 6), CobolSourceFormatUtils.getBlankSequenceArea(format));
	}
}
