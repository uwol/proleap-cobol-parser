/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.runner;

import java.io.File;
import java.io.IOException;

import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public interface CobolParserRunner {

	Program analyzeCode(String cobolCode, String compilationUnitName, CobolParserParams params) throws IOException;

	Program analyzeFile(File cobolFile, CobolParserParams params) throws IOException;

	Program analyzeFile(File cobolFile, CobolSourceFormatEnum format) throws IOException;
}
