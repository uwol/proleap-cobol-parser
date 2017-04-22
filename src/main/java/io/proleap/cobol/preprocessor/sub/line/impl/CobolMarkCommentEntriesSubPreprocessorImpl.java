/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.line.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.plexus.util.StringUtils;

import io.proleap.cobol.preprocessor.CobolPreprocessor;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolDialect;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormat;
import io.proleap.cobol.preprocessor.sub.impl.AbstractCobolSubPreprocessor;
import io.proleap.cobol.preprocessor.sub.impl.CobolLine;
import io.proleap.cobol.preprocessor.sub.line.CobolMarkCommentEntriesSubPreprocessor;

public class CobolMarkCommentEntriesSubPreprocessorImpl extends AbstractCobolSubPreprocessor
		implements CobolMarkCommentEntriesSubPreprocessor {

	protected final Pattern commentEntryTriggerLinePattern;

	protected boolean foundCommentEntryTriggerInPreviousLine = false;

	protected boolean inCommentEntry = false;

	protected final String[] triggersEnd = new String[] { "PROGRAM-ID.", "AUTHOR.", "INSTALLATION.", "DATE-WRITTEN.",
			"DATE-COMPILED.", "SECURITY.", "ENVIRONMENT", "DATA.", "PROCEDURE." };

	protected final String[] triggersStart = new String[] { "AUTHOR.", "INSTALLATION.", "DATE-WRITTEN.",
			"DATE-COMPILED.", "SECURITY.", "REMARKS." };

	public CobolMarkCommentEntriesSubPreprocessorImpl() {
		final String commentEntryTriggerLineFormat = new String("(" + String.join("|", triggersStart) + ")(.+)");
		commentEntryTriggerLinePattern = Pattern.compile(commentEntryTriggerLineFormat, Pattern.CASE_INSENSITIVE);
	}

	protected boolean beginsWithTrigger(final CobolLine parsedLine, final String[] triggers) {
		final String contentAreaUpperCase = new String(parsedLine.contentAreaA + parsedLine.contentAreaB).toUpperCase();

		boolean result = false;

		for (final String trigger : triggers) {
			final boolean containsTrigger = contentAreaUpperCase.startsWith(trigger);

			if (containsTrigger) {
				result = true;
				break;
			}
		}

		return result;
	}

	@Override
	public String processLine(final String line, final int lineNumber, final CobolDialect dialect,
			final CobolSourceFormat format) {
		final String result;

		if (format.isCommentEntryMultiLine()) {
			result = processSourceFormat(line, lineNumber, dialect, format);
		} else {
			result = line + CobolPreprocessor.NEWLINE;
		}

		return result;
	}

	/**
	 * If the Compiler directive SOURCEFORMAT is specified as or defaulted to
	 * FIXED, the comment-entry can be contained on one or more lines but is
	 * restricted to area B of those lines; the next line commencing in area A
	 * begins the next non-comment entry.
	 */
	protected String processSourceFormat(final String line, final int lineNumber, final CobolDialect dialect,
			final CobolSourceFormat format) {
		final CobolLine parsedLine = parseCobolLine(line, format);

		if (parsedLine == null) {
			throwCobolLineParseException(line, lineNumber, format);
		}

		final boolean foundCommentEntryTriggerInCurrentLine = beginsWithTrigger(parsedLine, triggersStart);

		final String result;

		if (foundCommentEntryTriggerInCurrentLine) {
			result = removeCommentEntry(line, parsedLine);
		} else if (foundCommentEntryTriggerInPreviousLine || inCommentEntry) {
			final boolean isContentAreaAEmpty = parsedLine.contentAreaA.trim().isEmpty();

			/**
			 * OSVS: The comment-entry can be contained in either area A or area
			 * B of the comment-entry lines. However, the next occurrence in
			 * area A of any one of the following COBOL words or phrases
			 * terminates the comment-entry and begin the next paragraph or
			 * division.
			 */
			final boolean inOsvsCommentEntry = CobolDialect.OSVS.equals(dialect)
					&& !beginsWithTrigger(parsedLine, triggersEnd);

			inCommentEntry = parsedLine.indicatorArea == charAsterisk || parsedLine.indicatorArea == charSlash
					|| isContentAreaAEmpty || inOsvsCommentEntry;

			if (inCommentEntry) {
				result = parsedLine.sequenceArea + charAsterisk + parsedLine.contentAreaA + parsedLine.contentAreaB
						+ parsedLine.comment + CobolPreprocessor.NEWLINE;
			} else {
				result = line + CobolPreprocessor.NEWLINE;
			}
		} else {
			result = line + CobolPreprocessor.NEWLINE;
		}

		foundCommentEntryTriggerInPreviousLine = foundCommentEntryTriggerInCurrentLine;

		return result;
	}

	protected String removeCommentEntry(final String line, final CobolLine parsedLine) {
		final String result;

		final Matcher matcher = commentEntryTriggerLinePattern
				.matcher(parsedLine.contentAreaA + parsedLine.contentAreaB);

		if (matcher.matches()) {
			final String trigger = matcher.group(1);
			final String commentEntry = matcher.group(2);
			final String newContentArea = trigger + StringUtils.repeat(" ", commentEntry.length());

			result = parsedLine.sequenceArea + parsedLine.indicatorArea + newContentArea + parsedLine.comment
					+ CobolPreprocessor.NEWLINE;
		} else {
			result = line + CobolPreprocessor.NEWLINE;
		}

		return result;
	}
}
