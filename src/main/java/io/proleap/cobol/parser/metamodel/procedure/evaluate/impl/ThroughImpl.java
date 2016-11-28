/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.evaluate.impl;

import io.proleap.cobol.Cobol85Parser.EvaluateThroughContext;
import io.proleap.cobol.Cobol85Parser.EvaluateValueContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.Through;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.Value;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

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
