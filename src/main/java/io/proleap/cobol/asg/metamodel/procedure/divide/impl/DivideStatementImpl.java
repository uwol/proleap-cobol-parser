/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide.impl;

import io.proleap.cobol.CobolParser.DivideByGivingStatementContext;
import io.proleap.cobol.CobolParser.DivideIntoContext;
import io.proleap.cobol.CobolParser.DivideIntoGivingStatementContext;
import io.proleap.cobol.CobolParser.DivideIntoStatementContext;
import io.proleap.cobol.CobolParser.DivideRemainderContext;
import io.proleap.cobol.CobolParser.DivideStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideByGivingStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideIntoGivingStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideIntoStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.Remainder;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class DivideStatementImpl extends StatementImpl implements DivideStatement {

	protected final DivideStatementContext ctx;

	protected DivideByGivingStatement divideByGivingStatement;

	protected DivideIntoGivingStatement divideIntoGivingStatement;

	protected DivideIntoStatement divideIntoStatement;

	protected DivideType divideType;

	protected NotOnSizeErrorPhrase notOnSizeErrorPhrase;

	protected OnSizeErrorPhrase onSizeErrorPhrase;

	protected ValueStmt operandValueStmt;

	protected Remainder remainder;

	protected final StatementType statementType = StatementTypeEnum.DIVIDE;

	public DivideStatementImpl(final ProgramUnit programUnit, final Scope scope, final DivideStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public DivideByGivingStatement addDivideByGivingStatement(final DivideByGivingStatementContext ctx) {
		DivideByGivingStatement result = (DivideByGivingStatement) getASGElement(ctx);

		if (result == null) {
			result = new ByGivingImpl(programUnit, ctx);

			// by
			final ValueStmt byValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setByValueStmt(byValueStmt);

			// giving
			if (ctx.divideGivingPhrase() != null) {
				result.addGivingPhrase(ctx.divideGivingPhrase());
			}

			divideByGivingStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DivideIntoGivingStatement addDivideIntoGivingStatement(final DivideIntoGivingStatementContext ctx) {
		DivideIntoGivingStatement result = (DivideIntoGivingStatement) getASGElement(ctx);

		if (result == null) {
			result = new DivideIntoGivingStatementImpl(programUnit, ctx);

			// into
			final ValueStmt intoValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setIntoValueStmt(intoValueStmt);

			// givings
			if (ctx.divideGivingPhrase() != null) {
				result.addGivingPhrase(ctx.divideGivingPhrase());
			}

			divideIntoGivingStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DivideIntoStatement addDivideIntoStatement(final DivideIntoStatementContext ctx) {
		DivideIntoStatement result = (DivideIntoStatement) getASGElement(ctx);

		if (result == null) {
			result = new DivideIntoStatementImpl(programUnit, ctx);

			for (final DivideIntoContext divideIntoContext : ctx.divideInto()) {
				result.addInto(divideIntoContext);
			}

			divideIntoStatement = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Remainder addRemainder(final DivideRemainderContext ctx) {
		Remainder result = (Remainder) getASGElement(ctx);

		if (result == null) {
			result = new RemainderImpl(programUnit, ctx);

			// call
			if (ctx.identifier() != null) {
				final Call remainderCall = createCall(ctx.identifier());
				result.setRemainderCall(remainderCall);
			}

			remainder = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DivideByGivingStatement getDivideByGivingStatement() {
		return divideByGivingStatement;
	}

	@Override
	public DivideIntoGivingStatement getDivideIntoGivingStatement() {
		return divideIntoGivingStatement;
	}

	@Override
	public DivideIntoStatement getDivideIntoStatement() {
		return divideIntoStatement;
	}

	@Override
	public DivideType getDivideType() {
		return divideType;
	}

	@Override
	public NotOnSizeErrorPhrase getNotOnSizeErrorPhrase() {
		return notOnSizeErrorPhrase;
	}

	@Override
	public OnSizeErrorPhrase getOnSizeErrorPhrase() {
		return onSizeErrorPhrase;
	}

	@Override
	public ValueStmt getOperandValueStmt() {
		return operandValueStmt;
	}

	@Override
	public Remainder getRemainder() {
		return remainder;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setDivideType(final DivideType divideType) {
		this.divideType = divideType;
	}

	@Override
	public void setNotOnSizeErrorPhrase(final NotOnSizeErrorPhrase notOnSizeErrorPhrase) {
		this.notOnSizeErrorPhrase = notOnSizeErrorPhrase;
	}

	@Override
	public void setOnSizeErrorPhrase(final OnSizeErrorPhrase onSizeErrorPhrase) {
		this.onSizeErrorPhrase = onSizeErrorPhrase;
	}

	@Override
	public void setOperandValueStmt(final ValueStmt operandValueStmt) {
		this.operandValueStmt = operandValueStmt;
	}
}
