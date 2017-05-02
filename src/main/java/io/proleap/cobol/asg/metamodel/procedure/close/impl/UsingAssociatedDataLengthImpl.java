/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close.impl;

import io.proleap.cobol.Cobol85Parser.ClosePortFileIOUsingAssociatedDataLengthContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.close.UsingAssociatedDataLength;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class UsingAssociatedDataLengthImpl extends CobolDivisionElementImpl implements UsingAssociatedDataLength {

	protected ClosePortFileIOUsingAssociatedDataLengthContext ctx;

	protected ValueStmt dataLengthValueStmt;

	public UsingAssociatedDataLengthImpl(final ProgramUnit programUnit,
			final ClosePortFileIOUsingAssociatedDataLengthContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getDataLengthValueStmt() {
		return dataLengthValueStmt;
	}

	@Override
	public void setDataLengthValueStmt(final ValueStmt dataLengthValueStmt) {
		this.dataLengthValueStmt = dataLengthValueStmt;
	}

}
