/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call.impl;

import io.proleap.cobol.CobolParser.CallByValueContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.ByValue;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ByValueImpl extends CobolDivisionElementImpl implements ByValue {

	protected ByValueType byValueType;

	protected final CallByValueContext ctx;

	protected ValueStmt valueStmt;

	public ByValueImpl(final ProgramUnit programUnit, final CallByValueContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByValueType getByValueType() {
		return byValueType;
	}

	@Override
	public ValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public void setByValueType(final ByValueType byValueType) {
		this.byValueType = byValueType;
	}

	@Override
	public void setValueStmt(final ValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}
}
