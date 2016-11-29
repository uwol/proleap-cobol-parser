/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.call.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.CallByContentContext;
import io.proleap.cobol.Cobol85Parser.CallByContentStatementContext;
import io.proleap.cobol.Cobol85Parser.CallByReferenceContext;
import io.proleap.cobol.Cobol85Parser.CallByReferenceStatementContext;
import io.proleap.cobol.Cobol85Parser.CallByValueContext;
import io.proleap.cobol.Cobol85Parser.CallByValueStatementContext;
import io.proleap.cobol.Cobol85Parser.CallGivingPhraseContext;
import io.proleap.cobol.Cobol85Parser.CallStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.Scope;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.NotOnException;
import io.proleap.cobol.parser.metamodel.procedure.OnException;
import io.proleap.cobol.parser.metamodel.procedure.OnOverflow;
import io.proleap.cobol.parser.metamodel.procedure.call.CallByContentStatement;
import io.proleap.cobol.parser.metamodel.procedure.call.CallByReferenceStatement;
import io.proleap.cobol.parser.metamodel.procedure.call.CallByValueStatement;
import io.proleap.cobol.parser.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.parser.metamodel.procedure.call.Giving;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class CallStatementImpl extends StatementImpl implements CallStatement {

	protected List<CallByContentStatement> callByContentStatements = new ArrayList<CallByContentStatement>();

	protected List<CallByReferenceStatement> callByReferenceStatements = new ArrayList<CallByReferenceStatement>();

	protected List<CallByValueStatement> callByValueStatements = new ArrayList<CallByValueStatement>();

	protected final CallStatementContext ctx;

	protected Giving giving;

	protected NotOnException notOnException;

	protected OnException onException;

	protected OnOverflow onOverflow;

	protected Call programCall;

	public CallStatementImpl(final ProgramUnit programUnit, final Scope scope, final CallStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public CallByContentStatement addCallByContentStatement(final CallByContentStatementContext ctx) {
		CallByContentStatement result = (CallByContentStatement) getASGElement(ctx);

		if (result == null) {
			result = new CallByContentStatementImpl(programUnit, ctx);

			for (final CallByContentContext callByContentContext : ctx.callByContent()) {
				result.addByContent(callByContentContext);
			}

			callByContentStatements.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CallByReferenceStatement addCallByReferenceStatement(final CallByReferenceStatementContext ctx) {
		CallByReferenceStatement result = (CallByReferenceStatement) getASGElement(ctx);

		if (result == null) {
			result = new CallByReferenceStatementImpl(programUnit, ctx);

			for (final CallByReferenceContext callByReferenceContext : ctx.callByReference()) {
				result.addByReference(callByReferenceContext);
			}

			callByReferenceStatements.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CallByValueStatement addCallByValueStatement(final CallByValueStatementContext ctx) {
		CallByValueStatement result = (CallByValueStatement) getASGElement(ctx);

		if (result == null) {
			result = new CallByValueStatementImpl(programUnit, ctx);

			for (final CallByValueContext callByValueContext : ctx.callByValue()) {
				result.addValueStmt(callByValueContext);
			}

			callByValueStatements.add(result);
			registerASGElement(result);
		}

		return result;
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
	public List<CallByContentStatement> getCallByContentStatements() {
		return callByContentStatements;
	}

	@Override
	public List<CallByReferenceStatement> getCallByReferenceStatements() {
		return callByReferenceStatements;
	}

	@Override
	public List<CallByValueStatement> getCallByValueStatements() {
		return callByValueStatements;
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
	public Call getProgramCall() {
		return programCall;
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
	public void setProgramCall(final Call programCall) {
		this.programCall = programCall;
	}

}
