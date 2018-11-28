package io.proleap.cobol.util;

public class CobolTestStringUtils {

	/**
	 * To be removed, as soon as the grammar does not require NEWLINEs and WS
	 * anymore
	 */
	public static String cleanFileTree(final String input) {
		final String inputNoEscapedNewline = input.replace("\\r", "").replace("\\n", "");

		final String inputNoNewline = inputNoEscapedNewline.replace("\r", "").replace("\n", "");
		final String inputReducedWhitespace = inputNoNewline.replaceAll("[\\s]+", " ").replaceAll("[\\s]+\\)", ")");
		final String result = inputReducedWhitespace;
		return result;
	}
}
