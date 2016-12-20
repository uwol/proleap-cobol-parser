/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.impl;

import io.proleap.cobol.Cobol85Parser.BasisContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.BasisValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class BasisValueStmtImpl extends ValueStmtImpl implements BasisValueStmt {

	protected ValueStmt basisValueStmt;

	protected BasisContext ctx;

	public BasisValueStmtImpl(final ProgramUnit programUnit, final BasisContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public ValueStmt getBasisValueStmt() {
		return basisValueStmt;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void setBasisValueStmt(final ValueStmt basisValueStmt) {
		this.basisValueStmt = basisValueStmt;
	}

}
