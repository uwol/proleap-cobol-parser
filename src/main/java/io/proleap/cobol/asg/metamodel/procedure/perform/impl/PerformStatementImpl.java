/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolParser.PerformInlineStatementContext;
import io.proleap.cobol.CobolParser.PerformProcedureStatementContext;
import io.proleap.cobol.CobolParser.PerformStatementContext;
import io.proleap.cobol.CobolParser.ProcedureNameContext;
import io.proleap.cobol.CobolParser.StatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformInlineStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformProcedureStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;

public class PerformStatementImpl extends StatementImpl implements PerformStatement {

	private final static Logger LOG = LoggerFactory.getLogger(PerformStatementImpl.class);

	protected final PerformStatementContext ctx;

	protected PerformInlineStatement performInlineStatement;

	protected PerformProcedureStatement performProcedureStatement;

	protected PerformStatementType performStatementType;

	protected final StatementType statementType = StatementTypeEnum.PERFORM;

	public PerformStatementImpl(final ProgramUnit programUnit, final Scope scope, final PerformStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public PerformInlineStatement addPerformInlineStatement(final PerformInlineStatementContext ctx) {
		PerformInlineStatement result = (PerformInlineStatement) getASGElement(ctx);

		if (result == null) {
			result = new PerformInlineStatementImpl(programUnit, ctx);

			// type
			if (ctx.performType() != null) {
				result.addPerformType(ctx.performType());
			}

			// statements
			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			performInlineStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PerformProcedureStatement addPerformProcedureStatement(final PerformProcedureStatementContext ctx) {
		PerformProcedureStatement result = (PerformProcedureStatement) getASGElement(ctx);

		if (result == null) {
			result = new PerformProcedureStatementImpl(programUnit, ctx);

			/*
			 * procedures
			 */
			final List<ProcedureNameContext> procedureNames = ctx.procedureName();

			if (procedureNames.isEmpty()) {
				LOG.warn("no calls in {}.", ctx);
			} else {
				final Call firstCall = createCall(procedureNames.get(0));
				result.addCall(firstCall);

				if (procedureNames.size() > 1) {
					final Call lastCall = createCall(procedureNames.get(1));
					final List<Call> callsThrough = addCallsThrough(firstCall, lastCall, ctx);

					result.addCalls(callsThrough);
					result.addCall(lastCall);
				}
			}

			/*
			 * type
			 */
			if (ctx.performType() != null) {
				result.addPerformType(ctx.performType());
			}

			performProcedureStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PerformInlineStatement getPerformInlineStatement() {
		return performInlineStatement;
	}

	@Override
	public PerformProcedureStatement getPerformProcedureStatement() {
		return performProcedureStatement;
	}

	@Override
	public PerformStatementType getPerformStatementType() {
		return performStatementType;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setPerformStatementType(final PerformStatementType performStatementType) {
		this.performStatementType = performStatementType;
	}
}
