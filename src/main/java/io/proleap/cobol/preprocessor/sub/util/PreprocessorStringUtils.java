package io.proleap.cobol.preprocessor.sub.util;

public class PreprocessorStringUtils {

	public static String trimQuotes(final String input) {
		final String result = input.replaceAll("^[\"']|[\"']$", "");
		return result;
	}
}
