/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.line.reader.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.preprocessor.CobolPreprocessor;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.exception.CobolPreprocessorException;
import io.proleap.cobol.preprocessor.sub.CobolLine;
import io.proleap.cobol.preprocessor.sub.CobolLineTypeEnum;
import io.proleap.cobol.preprocessor.sub.line.reader.CobolLineReader;

public class CobolLineReaderImpl implements CobolLineReader {

	protected static boolean isIndicatorChar(final char c) {
		return c == ' ' || c == '*' || c == '/' || c == '-' || c == '$'
				|| c == 'D' || c == 'd' || c == 'A' || c == 'B' || c == 'C'
				|| c == '\t' || c == '#';
	}

	protected CobolLineTypeEnum determineType(final String indicatorArea) {
		final CobolLineTypeEnum result;

		switch (indicatorArea) {
		case CobolPreprocessor.CHAR_D:
		case CobolPreprocessor.CHAR_D_:
			result = CobolLineTypeEnum.DEBUG;
			break;
		case CobolPreprocessor.CHAR_MINUS:
			result = CobolLineTypeEnum.CONTINUATION;
			break;
		case CobolPreprocessor.CHAR_ASTERISK:
		case CobolPreprocessor.CHAR_SLASH:
			result = CobolLineTypeEnum.COMMENT;
			break;
		case CobolPreprocessor.CHAR_DOLLAR_SIGN:
			result = CobolLineTypeEnum.COMPILER_DIRECTIVE;
			break;
		case CobolPreprocessor.WS:
		default:
			result = CobolLineTypeEnum.NORMAL;
			break;
		}

		return result;
	}

	@Override
	public CobolLine parseLine(final String line, final int lineNumber, final CobolParserParams params) {
		final CobolSourceFormatEnum format = params.getFormat();
		final int len = line.length();

		final String sequenceArea;
		final String indicatorArea;
		final String contentAreaA;
		final String contentAreaB;
		final String commentArea;

		switch (format) {
		case FIXED: {
			if (len <= 6) {
				sequenceArea = line;
				indicatorArea = " ";
				contentAreaA = "";
				contentAreaB = "";
				commentArea = "";
			} else {
				final char ind = line.charAt(6);
				if (!isIndicatorChar(ind)) {
					final String formatDescription = "Columns 1-6 sequence number, column 7 indicator area, columns 8-72 for areas A and B";
					final String message = "Is " + params.getFormat() + " the correct line format (" + formatDescription
							+ ")? Could not parse line " + (lineNumber + 1) + ": " + line;
					throw new CobolPreprocessorException(message);
				}
				sequenceArea = line.substring(0, 6);
				indicatorArea = String.valueOf(ind);
				contentAreaA = len > 7 ? line.substring(7, Math.min(11, len)) : "";
				contentAreaB = len > 11 ? line.substring(11, Math.min(72, len)) : "";
				commentArea = len > 72 ? line.substring(72) : "";
			}
			break;
		}
		case VARIABLE: {
			if (len <= 6) {
				sequenceArea = line;
				indicatorArea = " ";
				contentAreaA = "";
				contentAreaB = "";
				commentArea = "";
			} else {
				final char ind = line.charAt(6);
				if (!isIndicatorChar(ind)) {
					final String formatDescription = "Columns 1-6 sequence number, column 7 indicator area, columns 8 and all following for areas A and B";
					final String message = "Is " + params.getFormat() + " the correct line format (" + formatDescription
							+ ")? Could not parse line " + (lineNumber + 1) + ": " + line;
					throw new CobolPreprocessorException(message);
				}
				sequenceArea = line.substring(0, 6);
				indicatorArea = String.valueOf(ind);
				contentAreaA = len > 7 ? line.substring(7, Math.min(11, len)) : "";
				contentAreaB = len > 11 ? line.substring(11) : "";
				commentArea = "";
			}
			break;
		}
		case TANDEM: {
			sequenceArea = "";
			if (len == 0) {
				indicatorArea = " ";
				contentAreaA = "";
				contentAreaB = "";
				commentArea = "";
			} else {
				final char ind = line.charAt(0);
				if (!isIndicatorChar(ind)) {
					final String formatDescription = "Column 1 indicator area, columns 2 and all following for areas A and B";
					final String message = "Is " + params.getFormat() + " the correct line format (" + formatDescription
							+ ")? Could not parse line " + (lineNumber + 1) + ": " + line;
					throw new CobolPreprocessorException(message);
				}
				indicatorArea = String.valueOf(ind);
				contentAreaA = len > 1 ? line.substring(1, Math.min(5, len)) : "";
				contentAreaB = len > 5 ? line.substring(5) : "";
				commentArea = "";
			}
			break;
		}
		default:
			throw new CobolPreprocessorException("Unknown source format: " + format);
		}

		final CobolLineTypeEnum type = determineType(indicatorArea);
		return CobolLine.newCobolLine(sequenceArea, indicatorArea, contentAreaA, contentAreaB, commentArea,
				params.getFormat(), params.getDialect(), lineNumber, type);
	}

	@Override
	public List<CobolLine> processLines(final String lines, final CobolParserParams params) {
		if (lines == null || lines.isEmpty()) {
			return new ArrayList<CobolLine>();
		}

		final List<CobolLine> result = new ArrayList<CobolLine>();
		final int len = lines.length();
		CobolLine lastCobolLine = null;
		int lineNumber = 0;
		int start = 0;

		for (int i = 0; i < len; i++) {
			final char c = lines.charAt(i);
			if (c == '\r') {
				final String currentLine = lines.substring(start, i);
				if (i + 1 < len && lines.charAt(i + 1) == '\n') {
					i++;
				}
				start = i + 1;
				final CobolLine currentCobolLine = parseLine(currentLine, lineNumber++, params);
				currentCobolLine.setPredecessor(lastCobolLine);
				result.add(currentCobolLine);
				lastCobolLine = currentCobolLine;
			} else if (c == '\n') {
				final String currentLine = lines.substring(start, i);
				start = i + 1;
				final CobolLine currentCobolLine = parseLine(currentLine, lineNumber++, params);
				currentCobolLine.setPredecessor(lastCobolLine);
				result.add(currentCobolLine);
				lastCobolLine = currentCobolLine;
			}
		}

		if (start < len) {
			final String currentLine = lines.substring(start);
			final CobolLine currentCobolLine = parseLine(currentLine, lineNumber++, params);
			currentCobolLine.setPredecessor(lastCobolLine);
			result.add(currentCobolLine);
		}

		return result;
	}
}
