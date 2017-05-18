/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide.impl;

import io.proleap.cobol.Cobol85Parser.DivideByGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideGivingContext;
import io.proleap.cobol.Cobol85Parser.DivideIntoGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideIntoStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideRemainderContext;
import io.proleap.cobol.Cobol85Parser.DivideStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.Into;
import io.proleap.cobol.asg.metamodel.procedure.divide.ByGiving;
import io.proleap.cobol.asg.metamodel.procedure.divide.IntoGiving;
import io.proleap.cobol.asg.metamodel.procedure.divide.Remainder;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class DivideStatementImpl extends StatementImpl implements DivideStatement {

	protected final DivideStatementContext ctx;

	protected ValueStmt divisorValueStmt;

	protected Into into;

	protected ByGiving intoByGiving;

	protected IntoGiving intoGiving;

	protected NotOnSizeError notOnSizeError;

	protected OnSizeError onSizeError;

	protected Remainder remainder;

	protected final StatementType statementType = StatementTypeEnum.DIVIDE;

	protected Type type;

	public DivideStatementImpl(final ProgramUnit programUnit, final Scope scope, final DivideStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Into addInto(final DivideIntoStatementContext ctx) {
		Into result = (Into) getASGElement(ctx);

		if (result == null) {
			result = new IntoImpl(programUnit, ctx);

			// into
			final ValueStmt intoValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setIntoValueStmt(intoValueStmt);

			// givings
			if (ctx.divideGivingPhrase() != null) {
				result.addGivings(ctx.divideGivingPhrase());
			}

			into = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ByGiving addIntoByGiving(final DivideByGivingStatementContext ctx) {
		ByGiving result = (ByGiving) getASGElement(ctx);

		if (result == null) {
			result = new ByGivingImpl(programUnit, ctx);

			// into
			final ValueStmt intoValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setIntoValueStmt(intoValueStmt);

			// giving
			if (ctx.divideGivingPhrase() != null) {
				result.addGivings(ctx.divideGivingPhrase());
			}

			intoByGiving = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public IntoGiving addIntoGiving(final DivideIntoGivingStatementContext ctx) {
		IntoGiving result = (IntoGiving) getASGElement(ctx);

		if (result == null) {
			result = new IntoGivingImpl(programUnit, ctx);

			for (final DivideGivingContext divideGivingContext : ctx.divideGiving()) {
				result.addGiving(divideGivingContext);
			}

			intoGiving = result;
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
			final Call remainderCall = createCall(ctx.identifier());
			result.setRemainderCall(remainderCall);

			remainder = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt getDivisorValueStmt() {
		return divisorValueStmt;
	}

	@Override
	public Into getInto() {
		return into;
	}

	@Override
	public ByGiving getIntoByGiving() {
		return intoByGiving;
	}

	@Override
	public IntoGiving getIntoGiving() {
		return intoGiving;
	}

	@Override
	public NotOnSizeError getNotOnSizeError() {
		return notOnSizeError;
	}

	@Override
	public OnSizeError getOnSizeError() {
		return onSizeError;
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
	public Type getType() {
		return type;
	}

	@Override
	public void setDivisorValueStmt(final ValueStmt divisorValueStmt) {
		this.divisorValueStmt = divisorValueStmt;
	}

	@Override
	public void setNotOnSizeError(final NotOnSizeError notOnSizeError) {
		this.notOnSizeError = notOnSizeError;
	}

	@Override
	public void setOnSizeError(final OnSizeError onSizeError) {
		this.onSizeError = onSizeError;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
