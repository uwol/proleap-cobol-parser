/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.copybook.impl;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.util.Strings;

import io.proleap.cobol.Cobol85PreprocessorParser.CobolWordContext;
import io.proleap.cobol.preprocessor.sub.copybook.CobolWordCopyBookFinder;

public class CobolWordCopyBookFinderImpl implements CobolWordCopyBookFinder {

	@Override
	public File findCopyBook(final List<File> copyBookFilesAndDirs, final List<String> copyBookExtensions,
			final CobolWordContext ctx) {
		for (final String copyBookExtension : copyBookExtensions) {
			for (final File copyBookCandidate : copyBookFilesAndDirs) {
				if (!copyBookCandidate.isDirectory()) {
					if (isMatchingCopyBook(copyBookCandidate, copyBookExtension, ctx)) {
						return copyBookCandidate;
					}
				} else {
					final File validCopyBook = findCopyBookInDirectory(copyBookCandidate, copyBookExtension, ctx);

					if (validCopyBook != null) {
						return validCopyBook;
					}
				}
			}
		}

		return null;
	}

	protected File findCopyBookInDirectory(final File copyBooksDirectory, final String copyBookExtension,
			final CobolWordContext ctx) {
		for (final File copyBookCandidate : copyBooksDirectory.listFiles()) {
			final boolean isMatchingCopyBook = isMatchingCopyBook(copyBookCandidate, copyBookExtension, ctx);

			if (isMatchingCopyBook) {
				return copyBookCandidate;
			}
		}

		return null;
	}

	protected boolean isMatchingCopyBook(final File copyBookCandidate, final String generatedCopyBookFilename) {
		final String copyBookCandidateName = copyBookCandidate.getName();
		final boolean result = generatedCopyBookFilename.equalsIgnoreCase(copyBookCandidateName);
		return result;
	}

	protected boolean isMatchingCopyBook(final File copyBookCandidate, final String copyBookExtension,
			final CobolWordContext ctx) {
		final String copyBookIdentifier = ctx.getText();
		final String generatedCopyBookFilename = Strings.isBlank(copyBookExtension) ? copyBookIdentifier
				: copyBookIdentifier + "." + copyBookExtension;
		return isMatchingCopyBook(copyBookCandidate, generatedCopyBookFilename);
	}
}
