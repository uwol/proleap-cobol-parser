/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.evaluate.impl;

import io.proleap.cobol.Cobol85Parser.EvaluateAlsoConditionContext;
import io.proleap.cobol.Cobol85Parser.EvaluateValueContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.AlsoCondition;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.Value;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class AlsoConditionImpl extends CobolDivisionElementImpl implements AlsoCondition {

	protected final EvaluateAlsoConditionContext ctx;

	protected Value value;

	public AlsoConditionImpl(final ProgramUnit programUnit, final EvaluateAlsoConditionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Value addValue(final EvaluateValueContext ctx) {
		Value result = (Value) getASGElement(ctx);

		if (result == null) {
			result = new ValueImpl(programUnit, ctx);

			// value
			final ValueStmt valueStmt;

			if (ctx.identifier() != null) {
				valueStmt = createCallValueStmt(ctx.identifier());
			} else if (ctx.literal() != null) {
				valueStmt = createLiteralValueStmt(ctx.literal());
			} else if (ctx.arithmeticExpression() != null) {
				valueStmt = createArithmeticValueStmt(ctx.arithmeticExpression());
			} else {
				valueStmt = null;
			}

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
