/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.params.impl;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import io.proleap.cobol.asg.params.CobolDialect;
import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CobolParserParamsImpl implements CobolParserParams {

	protected Charset charset = Charset.defaultCharset();

	protected List<File> copyBookDirectories;

	protected List<String> copyBookExtensions;

	protected List<File> copyBookFiles;

	protected CobolDialect dialect;

	protected CobolSourceFormatEnum format;

	protected boolean ignoreSyntaxErrors;

	@Override
	public Charset getCharset() {
		return charset;
	}

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
	public CobolSourceFormatEnum getFormat() {
		return format;
	}

	@Override
	public boolean getIgnoreSyntaxErrors() {
		return ignoreSyntaxErrors;
	}

	@Override
	public void setCharset(final Charset charset) {
		this.charset = charset;
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

	@Override
	public void setFormat(final CobolSourceFormatEnum format) {
		this.format = format;
	}

	@Override
	public void setIgnoreSyntaxErrors(final boolean ignoreSyntaxErrors) {
		this.ignoreSyntaxErrors = ignoreSyntaxErrors;
	}
}
