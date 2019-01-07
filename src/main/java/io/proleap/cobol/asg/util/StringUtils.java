package io.proleap.cobol.asg.util;

public class StringUtils {

	public static String lowercaseFirstLetter(final String data) {
		final char firstLetter = Character.toLowerCase(data.substring(0, 1).charAt(0));
		final String restLetters = data.substring(1);
		return firstLetter + restLetters;
	}
}
