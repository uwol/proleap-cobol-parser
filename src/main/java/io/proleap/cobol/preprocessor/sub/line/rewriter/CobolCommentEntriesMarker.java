/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.line.rewriter;

import io.proleap.cobol.preprocessor.sub.CobolLine;

/**
 * Preprocessor, which identifies and marks comment entries depending on the
 * COBOL dialect.
 */
public interface CobolCommentEntriesMarker extends CobolLineRewriter {

	CobolLine processLine(CobolLine line);
}
