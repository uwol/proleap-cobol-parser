/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.impl;

import java.io.File;
import java.util.List;

import io.proleap.cobol.preprocessor.CobolPreprocessorParams;
import io.proleap.cobol.preprocessor.params.CobolDialect;

public class CobolPreprocessorParamsImpl implements CobolPreprocessorParams {

	protected List<File> copyBookDirectories;

	protected List<String> copyBookExtensions;

	protected List<File> copyBookFiles;

	protected CobolDialect dialect;

	@Override
	public List<File> getCopyBookDirectories() {
		return copyBookDirectories;
	}

	@Override
	public List<String> getCopyBookExtensions() {
		return copyBookExtensions;
	}

	@Override
	public List<File> getCopyBookFiles() {
		return copyBookFiles;
	}

	@Override
	public CobolDialect getDialect() {
		return dialect;
	}

	@Override
	public void setCopyBookDirectories(final List<File> copyBookDirectories) {
		this.copyBookDirectories = copyBookDirectories;
	}

	@Override
	public void setCopyBookExtensions(final List<String> copyBookExtensions) {
		this.copyBookExtensions = copyBookExtensions;
	}

	@Override
	public void setCopyBookFiles(final List<File> copyBookFiles) {
		this.copyBookFiles = copyBookFiles;
	}

	@Override
	public void setDialect(final CobolDialect dialect) {
		this.dialect = dialect;
	}
}
