/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.set.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.SetToContext;
import io.proleap.cobol.CobolParser.SetToStatementContext;
import io.proleap.cobol.CobolParser.SetToValueContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.set.SetTo;
import io.proleap.cobol.asg.metamodel.procedure.set.To;
import io.proleap.cobol.asg.metamodel.procedure.set.Value;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class SetToImpl extends CobolDivisionElementImpl implements SetTo {

	protected SetToStatementContext ctx;

	protected List<To> tos = new ArrayList<To>();

	protected List<Value> values = new ArrayList<Value>();

	public SetToImpl(final ProgramUnit programUnit, final SetToStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public To addTo(final SetToContext ctx) {
		To result = (To) getASGElement(ctx);

		if (result == null) {
			result = new ToImpl(programUnit, ctx);

			if (ctx.identifier() != null) {
				final Call toCall = createCall(ctx.identifier());
				result.setToCall(toCall);
			}

			tos.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Value addValue(final SetToValueContext ctx) {
		Value result = (Value) getASGElement(ctx);

		if (result == null) {
			result = new ValueImpl(programUnit, ctx);

			// type
			final Value.ValueType type;

			if (ctx.ON() != null) {
				type = Value.ValueType.ON;
			} else if (ctx.OFF() != null) {
				type = Value.ValueType.OFF;
			} else if (ctx.ENTRY() != null) {
				type = Value.ValueType.ENTRY;
			} else {
				type = Value.ValueType.CALL;
			}

			result.setValueType(type);

			// call
			final ValueStmt valueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setValueStmt(valueStmt);

			values.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<To> getTos() {
		return tos;
	}

	@Override
	public List<Value> getValues() {
		return values;
	}

}
