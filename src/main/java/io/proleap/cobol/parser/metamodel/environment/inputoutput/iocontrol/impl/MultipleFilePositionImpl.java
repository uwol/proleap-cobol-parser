/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.impl;

import io.proleap.cobol.Cobol85Parser.MultipleFilePositionContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.MultipleFilePosition;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class MultipleFilePositionImpl extends CobolDivisionElementImpl implements MultipleFilePosition {

	protected final MultipleFilePositionContext ctx;

	protected ValueStmt fileNameValueStmt;

	protected IntegerLiteralValueStmt integerLiteralValueStmt;

	public MultipleFilePositionImpl(final ProgramUnit programUnit, final MultipleFilePositionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getFileNameValueStmt() {
		return fileNameValueStmt;
	}

	@Override
	public IntegerLiteralValueStmt getIntegerLiteralValueStmt() {
		return integerLiteralValueStmt;
	}

	@Override
	public void setFileNameValueStmt(final ValueStmt fileNameValueStmt) {
		this.fileNameValueStmt = fileNameValueStmt;
	}

	@Override
	public void setIntegerLiteralValueStmt(final IntegerLiteralValueStmt integerLiteralValueStmt) {
		this.integerLiteralValueStmt = integerLiteralValueStmt;
	}

}
