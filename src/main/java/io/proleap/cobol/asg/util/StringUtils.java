package io.proleap.cobol.asg.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

	public static String lowercaseFirstLetter(final String data) {
		final char firstLetter = Character.toLowerCase(data.substring(0, 1).charAt(0));
		final String restLetters = data.substring(1);
		return firstLetter + restLetters;
	}

	public static List<String> readLines(final InputStream inputStream, final Charset charset) throws IOException {
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
		final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		return bufferedReader.lines().collect(Collectors.toList());
	}

	public static String toString(final InputStream inputStream, final Charset charset) throws IOException {
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
		final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
	}
}
