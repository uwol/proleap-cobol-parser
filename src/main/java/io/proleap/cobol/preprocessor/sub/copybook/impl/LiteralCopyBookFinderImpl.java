/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.copybook.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolPreprocessorParser.LiteralContext;
import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.preprocessor.sub.copybook.LiteralCopyBookFinder;
import io.proleap.cobol.preprocessor.sub.util.PreprocessorStringUtils;

public class LiteralCopyBookFinderImpl implements LiteralCopyBookFinder {

	private final static Logger LOG = LoggerFactory.getLogger(LiteralCopyBookFinderImpl.class);

	@Override
	public File findCopyBook(final CobolParserParams params, final LiteralContext ctx) {
		if (params.getCopyBookFiles() != null) {
			for (final File copyBookFile : params.getCopyBookFiles()) {
				if (isMatchingCopyBook(copyBookFile, null, ctx)) {
					return copyBookFile;
				}
			}
		}

		if (params.getCopyBookDirectories() != null) {
			for (final File copyBookDirectory : params.getCopyBookDirectories()) {
				final File validCopyBook = findCopyBookInDirectory(copyBookDirectory, ctx);

				if (validCopyBook != null) {
					return validCopyBook;
				}
			}
		}

		return null;
	}

	protected File findCopyBookInDirectory(final File copyBooksDirectory, final LiteralContext ctx) {
		try {
			for (final File copyBookCandidate : Files.walk(copyBooksDirectory.toPath()).map(Path::toFile)
					.collect(Collectors.toList())) {
				if (isMatchingCopyBook(copyBookCandidate, copyBooksDirectory, ctx)) {
					return copyBookCandidate;
				}
			}
		} catch (final IOException e) {
			LOG.warn(e.getMessage(), e);
		}

		return null;
	}

	protected boolean isMatchingCopyBook(final File copyBookCandidate, final File cobolCopyDir,
			final LiteralContext ctx) {
		final String copyBookIdentifier = PreprocessorStringUtils.trimQuotes(ctx.getText()).replace("\\", "/");
		final boolean result;

		if (cobolCopyDir == null) {
			result = isMatchingCopyBookRelative(copyBookCandidate, copyBookIdentifier);
		} else {
			result = isMatchingCopyBookAbsolute(copyBookCandidate, cobolCopyDir, copyBookIdentifier);
		}

		return result;
	}

	protected boolean isMatchingCopyBookAbsolute(final File copyBookCandidate, final File cobolCopyDir,
			final String copyBookIdentifier) {
		final Path copyBookCandidateAbsolutePath = Paths.get(copyBookCandidate.getAbsolutePath()).normalize();
		final Path copyBookIdentifierAbsolutePath = Paths.get(cobolCopyDir.getAbsolutePath(), copyBookIdentifier)
				.normalize();
		final boolean result = copyBookIdentifierAbsolutePath.toString()
				.equalsIgnoreCase(copyBookCandidateAbsolutePath.toString());
		return result;
	}

	protected boolean isMatchingCopyBookRelative(final File copyBookCandidate, final String copyBookIdentifier) {
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
