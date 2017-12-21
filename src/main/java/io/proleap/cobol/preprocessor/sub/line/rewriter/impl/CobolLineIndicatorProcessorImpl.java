/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.line.rewriter.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;

import io.proleap.cobol.preprocessor.CobolPreprocessor;
import io.proleap.cobol.preprocessor.sub.CobolLine;
import io.proleap.cobol.preprocessor.sub.CobolLineTypeEnum;
import io.proleap.cobol.preprocessor.sub.line.rewriter.CobolLineIndicatorProcessor;

public class CobolLineIndicatorProcessorImpl implements CobolLineIndicatorProcessor {

	protected static final String EMPTY_STRING = "";

	protected String conditionalRightTrimContentArea(final CobolLine line) {
		final String result;

		if (!isNextLineContinuation(line)) {
			result = rightTrimContentArea(line.getContentArea());
		} else if (!isEndingWithOpenLiteral(line)) {
			result = rightTrimContentArea(line.getContentArea());
		} else {
			result = line.getContentArea();
		}

		return result;
	}

	protected boolean isEndingWithOpenLiteral(final CobolLine line) {
		final String contentArea = line.getContentAreaOriginal();
		final String contentAreaWithoutStringLiterals = removeStringLiterals(contentArea);
		return contentAreaWithoutStringLiterals.contains("\"") || contentAreaWithoutStringLiterals.contains("'");
	}

	protected boolean isNextLineContinuation(final CobolLine line) {
		return line.getSuccessor() == null ? false
				: CobolLineTypeEnum.CONTINUATION.equals(line.getSuccessor().getType());
	}

	/**
	 * Normalizes a line by stripping the sequence number and line indicator, and
	 * interpreting the line indicator.
	 */
	@Override
	public CobolLine processLine(final CobolLine line) {
		final String conditionalRightTrimmedContentArea = conditionalRightTrimContentArea(line);
		final CobolLine result;

		switch (line.getType()) {
		case DEBUG:
			result = CobolLine.copyCobolLineWithIndicatorAndContentArea(CobolPreprocessor.WS,
					conditionalRightTrimmedContentArea, line);
			break;
		case CONTINUATION:
			if (Strings.isBlank(conditionalRightTrimmedContentArea)) {
				result = CobolLine.copyCobolLineWithIndicatorAndContentArea(CobolPreprocessor.WS, EMPTY_STRING, line);
			}
			/**
			 * If a national or alphanumeric literal, which is continued on the next line
			 * has as the last character in column 72 a quotation mark ...
			 */
			else if (line.getPredecessor() != null && (line.getPredecessor().getContentAreaOriginal().endsWith("\"")
					|| line.getPredecessor().getContentAreaOriginal().endsWith("'"))) {
				/**
				 * ... the continuation line has to start with two consecutive quotation marks.
				 * This has to result in one single quotation mark in the value of the litera,
				 * expressed as 2 quotation marks in the literal for escaping.
				 */
				result = CobolLine.copyCobolLineWithIndicatorAndContentArea(CobolPreprocessor.WS,
						trimLeadingWhitespace(conditionalRightTrimmedContentArea).substring(1), line);
			}
			/**
			 * If the last character on the continued line of a national or alphanumeric
			 * literal is a single quotation mark in Area B ...
			 */
			else if (line.getPredecessor() != null && isEndingWithOpenLiteral(line.getPredecessor())) {
				final String trimmedContentArea = trimLeadingWhitespace(conditionalRightTrimmedContentArea);

				/**
				 * ... the continuation line might start with a single quotation mark.
				 */
				if (trimmedContentArea.startsWith("\"") || trimmedContentArea.startsWith("'")) {
					/**
					 * This has to result in 2 literals instead of one continued literal, 1 from
					 * continued line and 1 from continuing line.
					 */
					result = CobolLine.copyCobolLineWithIndicatorAndContentArea(CobolPreprocessor.WS,
							trimmedContentArea.substring(1), line);
				} else {
					result = CobolLine.copyCobolLineWithIndicatorAndContentArea(CobolPreprocessor.WS,
							conditionalRightTrimmedContentArea, line);
				}
			} else {
				result = CobolLine.copyCobolLineWithIndicatorAndContentArea(CobolPreprocessor.WS,
						trimLeadingWhitespace(conditionalRightTrimmedContentArea), line);
			}
			break;
		case COMMENT:
			result = CobolLine.copyCobolLineWithIndicatorAndContentArea(
					CobolPreprocessor.COMMENT_TAG + CobolPreprocessor.WS, conditionalRightTrimmedContentArea, line);
			break;
		case COMPILER_DIRECTIVE:
			result = CobolLine.copyCobolLineWithIndicatorAndContentArea(CobolPreprocessor.WS, EMPTY_STRING, line);
			break;
		case NORMAL:
		default:
			result = CobolLine.copyCobolLineWithIndicatorAndContentArea(CobolPreprocessor.WS,
					conditionalRightTrimmedContentArea, line);
			break;
		}

		return result;
	}

	@Override
	public List<CobolLine> processLines(final List<CobolLine> lines) {
		final List<CobolLine> result = new ArrayList<CobolLine>();

		for (final CobolLine line : lines) {
			final CobolLine processedLine = processLine(line);
			result.add(processedLine);
		}

		return result;
	}

	protected String removeStringLiterals(final String contentArea) {
		final String doubleQuoteLiteralPattern = "\"([^\"]|\"\"|'')*\"";
		final String singleQuoteLiteralPattern = "'([^']|''|\"\")*'";
		return contentArea.replaceAll(doubleQuoteLiteralPattern, EMPTY_STRING).replaceAll(singleQuoteLiteralPattern,
				EMPTY_STRING);
	}

	protected String repairTrailingComma(final String contentArea) {
		final String result;

		/*
		 * repair trimmed whitespace after comma separator
		 */
		if (contentArea.isEmpty()) {
			result = contentArea;
		} else {
			final char lastCharAtTrimmedLineArea = contentArea.charAt(contentArea.length() - 1);

			if (lastCharAtTrimmedLineArea == ',' || lastCharAtTrimmedLineArea == ';') {
				result = contentArea + CobolPreprocessor.WS;
			} else {
				result = contentArea;
			}
		}

		return result;
	}

	protected String rightTrimContentArea(final String contentarea) {
		final String contentAreaWithTrimmedTrailingWhitespace = trimTrailingWhitespace(contentarea);
		return repairTrailingComma(contentAreaWithTrimmedTrailingWhitespace);
	}

	protected String trimLeadingWhitespace(final String contentarea) {
		return contentarea.replaceAll("^\\s+", EMPTY_STRING);
	}

	protected String trimTrailingWhitespace(final String contentArea) {
		return contentArea.replaceAll("\\s+$", EMPTY_STRING);
	}
}
