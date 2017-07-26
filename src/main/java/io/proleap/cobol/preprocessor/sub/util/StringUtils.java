package io.proleap.cobol.preprocessor.sub.util;

public class StringUtils {

	public static String trimQuotes(final String input) {
		final String result = input.replaceAll("^[\"']|[\"']$", "");
		return result;
	}
}
