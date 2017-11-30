/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.copybook;

import java.io.File;

import io.proleap.cobol.Cobol85PreprocessorParser.CobolWordContext;
import io.proleap.cobol.preprocessor.CobolPreprocessorParams;

public interface CobolWordCopyBookFinder {

	File findCopyBook(CobolPreprocessorParams params, CobolWordContext ctx);
}
