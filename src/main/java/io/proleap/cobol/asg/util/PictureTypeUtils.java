/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.proleap.cobol.asg.metamodel.type.CobolBaseType;
import io.proleap.cobol.asg.metamodel.type.Type;

public class PictureTypeUtils {

	public static Pattern PATTERN_9 = Pattern.compile("9");

	public static Pattern PATTERN_99 = Pattern.compile("[9]{2,}");

	public static Pattern PATTERN_9Length = Pattern.compile("9\\(([0-9]+)\\)");

	public static Pattern PATTERN_9V9 = Pattern.compile("[9]+V[9]+");

	public static Pattern PATTERN_S9 = Pattern.compile("S9");

	public static Pattern PATTERN_S9Length = Pattern.compile("S9\\(([0-9]+)\\)");

	public static Pattern PATTERN_X = Pattern.compile("X");

	public static Pattern PATTERN_XLength = Pattern.compile("X\\(([0-9]+)\\)");

	public static Pattern PATTERN_XX = Pattern.compile("[X]{2,}");

	public static Type determineType(final String pictureString) {
		final Matcher matcher9 = PATTERN_9.matcher(pictureString);
		final Matcher matcher99 = PATTERN_99.matcher(pictureString);
		final Matcher matcher9Length = PATTERN_9Length.matcher(pictureString);
		final Matcher matcher9V9 = PATTERN_9V9.matcher(pictureString);
		final Matcher matcherS9 = PATTERN_S9.matcher(pictureString);
		final Matcher matcherS9Length = PATTERN_S9Length.matcher(pictureString);
		final Matcher matcherX = PATTERN_X.matcher(pictureString);
		final Matcher matcherXX = PATTERN_XX.matcher(pictureString);
		final Matcher matcherXLength = PATTERN_XLength.matcher(pictureString);

		final Type result;

		if (matcher9.matches()) {
			result = CobolBaseType.INTEGER;
		} else if (matcher99.matches()) {
			result = CobolBaseType.INTEGER;
		} else if (matcher9Length.matches()) {
			result = CobolBaseType.INTEGER;
		} else if (matcher9V9.matches()) {
			result = CobolBaseType.FLOAT;
		} else if (matcherS9.matches()) {
			result = CobolBaseType.INTEGER;
		} else if (matcherS9Length.matches()) {
			result = CobolBaseType.INTEGER;
		} else if (matcherX.matches()) {
			result = CobolBaseType.STRING;
		} else if (matcherXX.matches()) {
			result = CobolBaseType.STRING;
		} else if (matcherXLength.matches()) {
			result = CobolBaseType.STRING;
		} else {
			result = null;
		}

		return result;
	}
}
