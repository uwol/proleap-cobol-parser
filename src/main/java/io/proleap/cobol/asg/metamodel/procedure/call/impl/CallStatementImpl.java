/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call.impl;

import io.proleap.cobol.CobolParser.CallGivingPhraseContext;
import io.proleap.cobol.CobolParser.CallStatementContext;
import io.proleap.cobol.CobolParser.CallUsingParameterContext;
import io.proleap.cobol.CobolParser.CallUsingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.OnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.asg.metamodel.procedure.call.GivingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.call.UsingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class CallStatementImpl extends StatementImpl implements CallStatement {

	protected final CallStatementContext ctx;

	protected GivingPhrase givingPhrase;

	protected NotOnExceptionClause notOnExceptionClause;

	protected OnExceptionClause onExceptionClause;

	protected OnOverflowPhrase onOverflowPhrase;

	protected ValueStmt programValueStmt;

	protected final StatementType statementType = StatementTypeEnum.CALL;

	protected UsingPhrase usingPhrase;

	public CallStatementImpl(final ProgramUnit programUnit, final Scope scope, final CallStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public GivingPhrase addGivingPhrase(final CallGivingPhraseContext ctx) {
		GivingPhrase result = (GivingPhrase) getASGElement(ctx);

		if (result == null) {
			result = new GivingPhraseImpl(programUnit, ctx);

			if (ctx.identifier() != null) {
				final Call givingCall = createCall(ctx.identifier());
				result.setGivingCall(givingCall);
			}

			givingPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public UsingPhrase addUsingPhrase(final CallUsingPhraseContext ctx) {
		UsingPhrase result = (UsingPhrase) getASGElement(ctx);

		if (result == null) {
			result = new UsingPhraseImpl(programUnit, ctx);

			for (final CallUsingParameterContext callUsingParameterContext : ctx.callUsingParameter()) {
				result.addUsingParameter(callUsingParameterContext);
			}

			usingPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GivingPhrase getGivingPhrase() {
		return givingPhrase;
	}

	@Override
	public NotOnExceptionClause getNotOnExceptionClause() {
		return notOnExceptionClause;
	}

	@Override
	public OnExceptionClause getOnExceptionClause() {
		return onExceptionClause;
	}

	@Override
	public OnOverflowPhrase getOnOverflowPhrase() {
		return onOverflowPhrase;
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
	public UsingPhrase getUsingPhrase() {
		return usingPhrase;
	}

	@Override
	public void setNotOnExceptionClause(final NotOnExceptionClause notOnExceptionClause) {
		this.notOnExceptionClause = notOnExceptionClause;
	}

	@Override
	public void setOnExceptionClause(final OnExceptionClause onExceptionClause) {
		this.onExceptionClause = onExceptionClause;
	}

	@Override
	public void setOnOverflowPhrase(final OnOverflowPhrase onOverflowPhrase) {
		this.onOverflowPhrase = onOverflowPhrase;
	}

	@Override
	public void setProgramValueStmt(final ValueStmt programValueStmt) {
		this.programValueStmt = programValueStmt;
	}

}
