/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.line.impl;

import io.proleap.cobol.preprocessor.CobolPreprocessor;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolDialect;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormat;
import io.proleap.cobol.preprocessor.sub.impl.AbstractCobolSubPreprocessor;
import io.proleap.cobol.preprocessor.sub.line.CobolCleanLinesSubPreprocessor;

public class CobolCleanLinesSubPreprocessorImpl extends AbstractCobolSubPreprocessor
		implements CobolCleanLinesSubPreprocessor {

	@Override
	public String processLine(final String line, final int lineNumber, final CobolDialect dialect,
			final CobolSourceFormat format) {
		// clean line from certain ASCII chars
		final int substituteChar = 0x1A;
		final String cleanedLine = line.replace((char) substituteChar, ' ');
		final String result;

		// if line is empty
		if (cleanedLine.trim().isEmpty()) {
			result = "";
		} else {
			result = cleanedLine + CobolPreprocessor.NEWLINE;
		}

		return result;
	}
}
