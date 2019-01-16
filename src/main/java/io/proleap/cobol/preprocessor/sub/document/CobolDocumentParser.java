/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.document;

import io.proleap.cobol.asg.params.CobolParserParams;

public interface CobolDocumentParser {

	String processLines(String code, CobolParserParams params);
}
