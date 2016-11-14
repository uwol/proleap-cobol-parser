/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.impl;

import io.proleap.cobol.Cobol85Parser.MultipleFilePositionClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.MultipleFilePositionClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class MultipleFilePositionClauseImpl extends CobolDivisionElementImpl implements MultipleFilePositionClause {

	protected final MultipleFilePositionClauseContext ctx;

	protected ValueStmt fileNameValueStmt;

	protected IntegerLiteralValueStmt integerLiteralValueStmt;

	public MultipleFilePositionClauseImpl(final ProgramUnit programUnit, final MultipleFilePositionClauseContext ctx) {
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
