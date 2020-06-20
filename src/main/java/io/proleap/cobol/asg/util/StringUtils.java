package io.proleap.cobol.asg.util;

public class StringUtils {

	public static String capitalize(final String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String lowercaseFirstLetter(final String data) {
		final char firstLetter = Character.toLowerCase(data.substring(0, 1).charAt(0));
		final String restLetters = data.substring(1);
		return firstLetter + restLetters;
	}
}
