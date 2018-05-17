/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.impl;

import io.proleap.cobol.CobolParser.MultipleFilePositionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.MultipleFilePosition;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;

public class MultipleFilePositionImpl extends CobolDivisionElementImpl implements MultipleFilePosition {

	protected final MultipleFilePositionContext ctx;

	protected Call fileCall;

	protected IntegerLiteralValueStmt integerLiteralValueStmt;

	public MultipleFilePositionImpl(final ProgramUnit programUnit, final MultipleFilePositionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public IntegerLiteralValueStmt getIntegerLiteralValueStmt() {
		return integerLiteralValueStmt;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

	@Override
	public void setIntegerLiteralValueStmt(final IntegerLiteralValueStmt integerLiteralValueStmt) {
		this.integerLiteralValueStmt = integerLiteralValueStmt;
	}

}
