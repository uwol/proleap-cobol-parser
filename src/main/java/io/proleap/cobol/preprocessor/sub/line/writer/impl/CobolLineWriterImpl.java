/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.line.writer.impl;

import java.util.List;

import io.proleap.cobol.preprocessor.CobolPreprocessor;
import io.proleap.cobol.preprocessor.sub.CobolLine;
import io.proleap.cobol.preprocessor.sub.CobolLineTypeEnum;
import io.proleap.cobol.preprocessor.sub.line.writer.CobolLineWriter;

public class CobolLineWriterImpl implements CobolLineWriter {

	@Override
	public String serialize(final List<CobolLine> lines) {
		final StringBuffer sb = new StringBuffer();

		for (final CobolLine line : lines) {
			final boolean notContinuationLine = !CobolLineTypeEnum.CONTINUATION.equals(line.getType());

			if (notContinuationLine) {
				if (line.getNumber() > 0) {
					sb.append(CobolPreprocessor.NEWLINE);
				}

				sb.append(line.getBlankSequenceArea());
				sb.append(line.getIndicatorArea());
			}

			sb.append(line.getContentArea());
		}

		return sb.toString();
	}
}
