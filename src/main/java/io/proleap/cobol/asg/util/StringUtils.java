package io.proleap.cobol.asg.util;

public class StringUtils {

	public static String capitalize(final String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static int countMatches(final String str, final String target) {
		return (str.length() - str.replace(target, "").length()) / target.length();
	}

	public static String leftPad(final String str, final int size) {
		return leftPad(str, size, " ");
	}

	public static String leftPad(final String str, final int size, final String padChar) {
		final int pads = Math.max(0, size - str.length());
		return padChar.repeat(pads).concat(str);
	}

	public static String lowercaseFirstLetter(final String data) {
		final char firstLetter = Character.toLowerCase(data.substring(0, 1).charAt(0));
		final String restLetters = data.substring(1);
		return firstLetter + restLetters;
	}

	public static String rightPad(final String str, final int size) {
		return rightPad(str, size, " ");
	}

	public static String rightPad(final String str, final int size, final String padChar) {
		final int pads = Math.max(0, size - str.length());
		return str.concat(padChar.repeat(pads));
	}

	public static String substringAfter(final String str, final String separator) {
		final int pos = str.indexOf(separator);
		return pos == -1 ? "" : str.substring(pos + separator.length());
	}

	public static String substringBefore(final String str, final String separator) {
		final int pos = str.indexOf(separator);
		return pos == -1 ? str : str.substring(0, pos);
	}
}
