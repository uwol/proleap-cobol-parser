/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close.impl;

import io.proleap.cobol.Cobol85Parser.ClosePortFileIOUsingAssociatedDataContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.close.UsingAssociatedData;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class UsingAssociatedDataImpl extends CobolDivisionElementImpl implements UsingAssociatedData {

	protected ClosePortFileIOUsingAssociatedDataContext ctx;

	protected ValueStmt dataValueStmt;

	public UsingAssociatedDataImpl(final ProgramUnit programUnit, final ClosePortFileIOUsingAssociatedDataContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getDataValueStmt() {
		return dataValueStmt;
	}

	@Override
	public void setDataValueStmt(final ValueStmt dataValueStmt) {
		this.dataValueStmt = dataValueStmt;
	}

}
