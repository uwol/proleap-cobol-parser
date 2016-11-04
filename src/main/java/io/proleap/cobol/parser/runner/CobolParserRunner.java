/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.runner;

import java.io.File;
import java.io.IOException;

import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolDialect;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormat;

public interface CobolParserRunner {

	Program analyzeDirectory(File inputDirectory, CobolDialect dialect, CobolSourceFormat format)
			throws IOException;

	Program analyzeFile(File inputFile, CobolDialect dialect, CobolSourceFormat format) throws IOException;

}
