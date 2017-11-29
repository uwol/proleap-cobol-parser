/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import io.proleap.cobol.Cobol85PreprocessorParser.CobolWordContext;
import io.proleap.cobol.Cobol85PreprocessorParser.LiteralContext;
import io.proleap.cobol.preprocessor.sub.util.StringUtils;

public class CopyBookUtils {

	public static File findCopyBookByCobolWord(final List<File> copyBookFilesAndDirs, final CobolWordContext ctx) {
		for (final File copyBookCandidate : copyBookFilesAndDirs) {
			if (!copyBookCandidate.isDirectory()) {
				if (isMatchingCopyBookByCobolWord(copyBookCandidate, ctx)) {
					return copyBookCandidate;
				}
			} else {
				final File validCopyBook = findCopyBookByCobolWordInDirectory(copyBookCandidate, ctx);

				if (validCopyBook != null) {
					return validCopyBook;
				}
			}
		}

		return null;
	}

	protected static File findCopyBookByCobolWordInDirectory(final File copyBooksDirectory,
			final CobolWordContext ctx) {
		for (final File copyBookCandidate : copyBooksDirectory.listFiles()) {
			final boolean isMatchingCopyBook = isMatchingCopyBookByCobolWord(copyBookCandidate, ctx);

			if (isMatchingCopyBook) {
				return copyBookCandidate;
			}
		}

		return null;
	}

	public static File findCopyBookByLiteral(final List<File> copyBookFilesAndDirs, final LiteralContext ctx) {
		for (final File copyBookCandidate : copyBookFilesAndDirs) {
			if (!copyBookCandidate.isDirectory()) {
				if (isMatchingCopyBookByLiteral(copyBookCandidate, null, ctx)) {
					return copyBookCandidate;
				}
			} else {
				final File validCopyBook = findCopyBookByLiteralInDirectory(copyBookCandidate, ctx);

				if (validCopyBook != null) {
					return validCopyBook;
				}
			}
		}

		return null;
	}

	protected static File findCopyBookByLiteralInDirectory(final File copyBooksDirectory, final LiteralContext ctx) {
		for (final File copyBookCandidate : FileUtils.listFiles(copyBooksDirectory, null, true)) {
			final boolean isMatchingCopyBook = isMatchingCopyBookByLiteral(copyBookCandidate, copyBooksDirectory, ctx);

			if (isMatchingCopyBook) {
				return copyBookCandidate;
			}
		}

		return null;
	}

	protected static boolean isMatchingCopyBookByCobolWord(final File copyBookCandidate, final CobolWordContext ctx) {
		final String copyBookIdentifier = ctx.getText();
		return isMatchingCopyBookByCobolWord(copyBookCandidate, copyBookIdentifier);
	}

	protected static boolean isMatchingCopyBookByCobolWord(final File copyBookCandidate,
			final String copyBookIdentifier) {
		final String baseName = FilenameUtils.getBaseName(copyBookCandidate.getName());
		final boolean result = copyBookIdentifier.toLowerCase().equals(baseName.toLowerCase());
		return result;
	}

	protected static boolean isMatchingCopyBookByLiteral(final File copyBookCandidate, final File cobolCopyDir,
			final LiteralContext ctx) {
		final String copyBookIdentifier = StringUtils.trimQuotes(ctx.getText()).replace("\\", "/");
		final boolean result;

		if (cobolCopyDir == null) {
			result = isMatchingCopyBookByLiteralRelative(copyBookCandidate, copyBookIdentifier);
		} else {
			result = isMatchingCopyBookByLiteralAbsolute(copyBookCandidate, cobolCopyDir, copyBookIdentifier);
		}

		return result;
	}

	protected static boolean isMatchingCopyBookByLiteralAbsolute(final File copyBookCandidate, final File cobolCopyDir,
			final String copyBookIdentifier) {
		final Path copyBookCandidateAbsolutePath = Paths.get(copyBookCandidate.getAbsolutePath()).normalize();
		final Path copyBookIdentifierAbsolutePath = Paths.get(cobolCopyDir.getAbsolutePath(), copyBookIdentifier)
				.normalize();
		final boolean result = copyBookIdentifierAbsolutePath.toString()
				.equalsIgnoreCase(copyBookCandidateAbsolutePath.toString());
		return result;
	}

	protected static boolean isMatchingCopyBookByLiteralRelative(final File copyBookCandidate,
			final String copyBookIdentifier) {
		final Path copyBookCandidateAbsolutePath = Paths.get(copyBookCandidate.getAbsolutePath()).normalize();
		final Path copyBookIdentifierRelativePath;

		if (copyBookIdentifier.startsWith("/") || copyBookIdentifier.startsWith("./")
				|| copyBookIdentifier.startsWith("\\") || copyBookIdentifier.startsWith(".\\")) {
			copyBookIdentifierRelativePath = Paths.get(copyBookIdentifier).normalize();
		} else {
			copyBookIdentifierRelativePath = Paths.get("/" + copyBookIdentifier).normalize();
		}

		final boolean result = copyBookCandidateAbsolutePath.toString().toLowerCase()
				.endsWith(copyBookIdentifierRelativePath.toString().toLowerCase());
		return result;
	}
}
