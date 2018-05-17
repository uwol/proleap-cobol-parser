/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.evaluate.impl;

import io.proleap.cobol.CobolParser.EvaluateValueContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.Value;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ValueImpl extends CobolDivisionElementImpl implements Value {

	protected final EvaluateValueContext ctx;

	protected ValueStmt valueStmt;

	public ValueImpl(final ProgramUnit programUnit, final EvaluateValueContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public void setValueStmt(final ValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}

}
