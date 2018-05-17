/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.evaluate.impl;

import io.proleap.cobol.CobolParser.EvaluateThroughContext;
import io.proleap.cobol.CobolParser.EvaluateValueContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.Through;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.Value;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ThroughImpl extends CobolDivisionElementImpl implements Through {

	protected final EvaluateThroughContext ctx;

	protected Value value;

	public ThroughImpl(final ProgramUnit programUnit, final EvaluateThroughContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Value addValue(final EvaluateValueContext ctx) {
		Value result = (Value) getASGElement(ctx);

		if (result == null) {
			result = new ValueImpl(programUnit, ctx);

			final ValueStmt valueStmt = createValueStmt(ctx.identifier(), ctx.literal(), ctx.arithmeticExpression());
			result.setValueStmt(valueStmt);

			value = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Value getValue() {
		return value;
	}

}
