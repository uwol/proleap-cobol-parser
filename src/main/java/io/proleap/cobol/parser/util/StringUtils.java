/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.util;

public class StringUtils {

	public static boolean isDouble(final String str) {
		try {
			Double.parseDouble(str);
		} catch (final NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean isInteger(final String str) {
		try {
			Integer.parseInt(str);
		} catch (final NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static Double parseDouble(final String str) {
		try {
			final Double d = Double.parseDouble(str);
			return d;
		} catch (final NumberFormatException nfe) {
			return null;
		}
	}

	public static Integer parseInteger(final String str) {
		try {
			final Integer i = Integer.parseInt(str);
			return i;
		} catch (final NumberFormatException nfe) {
			return null;
		}
	}
}
