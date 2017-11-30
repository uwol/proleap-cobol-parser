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
import java.util.List;

import io.proleap.cobol.preprocessor.CobolPreprocessorParams;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public interface CobolParseTestRunner {

	void parseFile(File inputFile, CobolSourceFormatEnum format) throws IOException;

	void parseFile(File inputFile, List<File> copyFiles, CobolSourceFormatEnum format, CobolPreprocessorParams params)
			throws IOException;

}
