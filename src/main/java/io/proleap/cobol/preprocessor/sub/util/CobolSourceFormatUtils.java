/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.util;

import com.google.common.base.Strings;

import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormat;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CobolSourceFormatUtils {

	private static final String WS = " ";

	public static String getBlankComments() {
		return Strings.repeat(WS, 8);
	}

	public static String getBlankContentArea() {
		return Strings.repeat(WS, 65);
	}

	public static String getBlankIndicatorArea() {
		return WS;
	}

	public static String getBlankLine(final CobolSourceFormat format) {
		final String result;

		if (format == null) {
			result = null;
		} else if (CobolSourceFormatEnum.TANDEM.equals(format)) {
			result = getBlankSequenceAndIndicatorArea(format);
		} else if (CobolSourceFormatEnum.VARIABLE.equals(format)) {
			result = getBlankSequenceAndIndicatorArea(format);
		} else {
			result = getBlankSequenceAndIndicatorArea(format) + getBlankContentArea() + getBlankComments();
		}

		return result;
	}

	public static String getBlankSequenceAndIndicatorArea(final CobolSourceFormat format) {
		final String result;

		if (format == null) {
			result = null;
		} else {
			result = getBlankSequenceArea(format) + getBlankIndicatorArea();
		}

		return result;
	}

	public static String getBlankSequenceArea(final CobolSourceFormat format) {
		final String result;

		if (format == null) {
			result = null;
		} else if (CobolSourceFormatEnum.TANDEM.equals(format)) {
			result = "";
		} else {
			result = Strings.repeat(WS, 6);
		}

		return result;
	}

}
