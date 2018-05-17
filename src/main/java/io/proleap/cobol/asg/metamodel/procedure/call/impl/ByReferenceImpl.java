/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call.impl;

import io.proleap.cobol.CobolParser.CallByReferenceContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.ByReference;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ByReferenceImpl extends CobolDivisionElementImpl implements ByReference {

	protected ByReferenceType byReferenceType;

	protected final CallByReferenceContext ctx;

	protected ValueStmt valueStmt;

	public ByReferenceImpl(final ProgramUnit programUnit, final CallByReferenceContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByReferenceType getByReferenceType() {
		return byReferenceType;
	}

	@Override
	public ValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public void setByReferenceType(final ByReferenceType byReferenceType) {
		this.byReferenceType = byReferenceType;
	}

	@Override
	public void setValueStmt(final ValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}
}
