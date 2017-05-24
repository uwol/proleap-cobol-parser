/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call.impl;

import io.proleap.cobol.Cobol85Parser.CallByContentContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.ByContent;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ByContentImpl extends CobolDivisionElementImpl implements ByContent {

	protected ByContentType byContentType;

	protected final CallByContentContext ctx;

	protected ValueStmt valueStmt;

	public ByContentImpl(final ProgramUnit programUnit, final CallByContentContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByContentType getByContentType() {
		return byContentType;
	}

	@Override
	public ValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public void setByContentType(final ByContentType byContentType) {
		this.byContentType = byContentType;
	}

	@Override
	public void setValueStmt(final ValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}

}
