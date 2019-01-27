/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.PerformProcedureStatementContext;
import io.proleap.cobol.CobolParser.PerformTypeContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformProcedureStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformType;

public class PerformProcedureStatementImpl extends CobolDivisionElementImpl implements PerformProcedureStatement {

	protected final List<Call> calls = new ArrayList<>();

	protected final PerformProcedureStatementContext ctx;

	protected PerformType performType;

	public PerformProcedureStatementImpl(final ProgramUnit programUnit, final PerformProcedureStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addCall(final Call call) {
		calls.add(call);
	}

	@Override
	public void addCalls(final List<Call> calls) {
		this.calls.addAll(calls);
	}

	@Override
	public PerformType addPerformType(final PerformTypeContext ctx) {
		PerformType result = (PerformType) getASGElement(ctx);

		if (result == null) {
			result = new PerformTypeImpl(programUnit, ctx);

			final PerformType.PerformTypeType type;

			if (ctx.performTimes() != null) {
				type = PerformType.PerformTypeType.TIMES;

				result.addTimes(ctx.performTimes());
			} else if (ctx.performUntil() != null) {
				type = PerformType.PerformTypeType.UNTIL;

				result.addUntil(ctx.performUntil());
			} else if (ctx.performVarying() != null) {
				type = PerformType.PerformTypeType.VARYING;

				result.addVarying(ctx.performVarying());
			} else {
				type = null;
			}

			result.setPerformTypeType(type);

			performType = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Call> getCalls() {
		return calls;
	}

	@Override
	public PerformType getPerformType() {
		return performType;
	}
}
