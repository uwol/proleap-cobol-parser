package org.cobol85.util;

public class StringUtils {

	/**
	 * To be removed, as soon as the COBOL grammar does not require NEWLINEs and
	 * WS anymore
	 */
	@Deprecated
	public static String cleanFileTree(final String inputFileTree) {
		final String inputFileTreeNoNewline = inputFileTree.replace("\\r", "").replace("\\n", "");
		final String inputFileTreeNoWhitespace = inputFileTreeNoNewline.replaceAll("[ ]+", " ").replace(" )", ")");
		final String result = inputFileTreeNoWhitespace;
		return result;
	}
}
