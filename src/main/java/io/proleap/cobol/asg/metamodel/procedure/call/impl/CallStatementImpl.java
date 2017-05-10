/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call.impl;

import io.proleap.cobol.Cobol85Parser.CallGivingPhraseContext;
import io.proleap.cobol.Cobol85Parser.CallStatementContext;
import io.proleap.cobol.Cobol85Parser.CallUsingParameterContext;
import io.proleap.cobol.Cobol85Parser.CallUsingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnException;
import io.proleap.cobol.asg.metamodel.procedure.OnException;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflow;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.asg.metamodel.procedure.call.Giving;
import io.proleap.cobol.asg.metamodel.procedure.call.Using;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class CallStatementImpl extends StatementImpl implements CallStatement {

	protected final CallStatementContext ctx;

	protected Giving giving;

	protected NotOnException notOnException;

	protected OnException onException;

	protected OnOverflow onOverflow;

	protected ValueStmt programValueStmt;

	protected final StatementType statementType = StatementTypeEnum.CALL;

	protected Using using;

	public CallStatementImpl(final ProgramUnit programUnit, final Scope scope, final CallStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Giving addGiving(final CallGivingPhraseContext ctx) {
		Giving result = (Giving) getASGElement(ctx);

		if (result == null) {
			result = new GivingImpl(programUnit, ctx);

			final Call givingCall = createCall(ctx.identifier());
			result.setGivingCall(givingCall);

			giving = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Using addUsing(final CallUsingPhraseContext ctx) {
		Using result = (Using) getASGElement(ctx);

		if (result == null) {
			result = new UsingImpl(programUnit, ctx);

			for (final CallUsingParameterContext callUsingParameterContext : ctx.callUsingParameter()) {
				result.addParameter(callUsingParameterContext);
			}

			using = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Giving getGiving() {
		return giving;
	}

	@Override
	public NotOnException getNotOnException() {
		return notOnException;
	}

	@Override
	public OnException getOnException() {
		return onException;
	}

	@Override
	public OnOverflow getOnOverflow() {
		return onOverflow;
	}

	@Override
	public ValueStmt getProgramValueStmt() {
		return programValueStmt;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public Using getUsing() {
		return using;
	}

	@Override
	public void setNotOnException(final NotOnException notOnException) {
		this.notOnException = notOnException;
	}

	@Override
	public void setOnException(final OnException onException) {
		this.onException = onException;
	}

	@Override
	public void setOnOverflow(final OnOverflow onOverflow) {
		this.onOverflow = onOverflow;
	}

	@Override
	public void setProgramValueStmt(final ValueStmt programValueStmt) {
		this.programValueStmt = programValueStmt;
	}

}
