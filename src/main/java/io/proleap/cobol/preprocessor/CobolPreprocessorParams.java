/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor;

import java.io.File;
import java.util.List;

import io.proleap.cobol.preprocessor.params.CobolDialect;

public interface CobolPreprocessorParams {

	List<File> getCopyBookDirectories();

	List<String> getCopyBookExtensions();

	List<File> getCopyBookFiles();

	CobolDialect getDialect();

	void setCopyBookDirectories(List<File> copyBookDirectories);

	void setCopyBookExtensions(List<String> copyBookExtensions);

	void setCopyBookFiles(List<File> copyBookFiles);

	void setDialect(CobolDialect dialect);
}
