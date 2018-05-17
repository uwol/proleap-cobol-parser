/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import io.proleap.cobol.CobolParser.PerformTimesContext;
import io.proleap.cobol.CobolParser.PerformTypeContext;
import io.proleap.cobol.CobolParser.PerformUntilContext;
import io.proleap.cobol.CobolParser.PerformVaryingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformType;
import io.proleap.cobol.asg.metamodel.procedure.perform.Times;
import io.proleap.cobol.asg.metamodel.procedure.perform.Until;
import io.proleap.cobol.asg.metamodel.procedure.perform.Varying;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class PerformTypeImpl extends CobolDivisionElementImpl implements PerformType {

	protected final PerformTypeContext ctx;

	protected PerformTypeType performTypeType;

	protected Times times;

	protected Until until;

	protected Varying varying;

	public PerformTypeImpl(final ProgramUnit programUnit, final PerformTypeContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Times addTimes(final PerformTimesContext ctx) {
		Times result = (Times) getASGElement(ctx);

		if (result == null) {
			result = new TimesImpl(programUnit, ctx);

			final ValueStmt timesValueStmt = createValueStmt(ctx.identifier(), ctx.integerLiteral());
			result.setTimesValueStmt(timesValueStmt);

			times = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Until addUntil(final PerformUntilContext ctx) {
		Until result = (Until) getASGElement(ctx);

		if (result == null) {
			result = new UntilImpl(programUnit, ctx);

			// test clause
			if (ctx.performTestClause() != null) {
				result.addTestClause(ctx.performTestClause());
			}

			// condition
			final ConditionValueStmt condition = createConditionValueStmt(ctx.condition());
			result.setCondition(condition);

			until = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Varying addVarying(final PerformVaryingContext ctx) {
		Varying result = (Varying) getASGElement(ctx);

		if (result == null) {
			result = new VaryingImpl(programUnit, ctx);

			if (ctx.performTestClause() != null) {
				result.addTestClause(ctx.performTestClause());
			}

			if (ctx.performVaryingClause() != null) {
				result.addVaryingClause(ctx.performVaryingClause());
			}

			varying = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PerformTypeType getPerformTypeType() {
		return performTypeType;
	}

	@Override
	public Times getTimes() {
		return times;
	}

	@Override
	public Until getUntil() {
		return until;
	}

	@Override
	public Varying getVarying() {
		return varying;
	}

	@Override
	public void setPerformTypeType(final PerformTypeType performTypeType) {
		this.performTypeType = performTypeType;
	}

}
