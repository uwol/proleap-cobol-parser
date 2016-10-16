/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.runner;

import java.io.File;
import java.io.IOException;

import io.proleap.cobol.preprocessor.Cobol85Preprocessor.Cobol85SourceFormatEnum;

public interface Cobol85ParseTestRunner {

	void parseDirectory(File inputDirectory, final Cobol85SourceFormatEnum format) throws IOException;

	void parseFile(File inputFile, Cobol85SourceFormatEnum format) throws IOException;

}
