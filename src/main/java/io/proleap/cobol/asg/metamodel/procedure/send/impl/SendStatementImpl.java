/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.send.impl;

import io.proleap.cobol.CobolParser.SendStatementAsyncContext;
import io.proleap.cobol.CobolParser.SendStatementContext;
import io.proleap.cobol.CobolParser.SendStatementSyncContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.OnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.send.Async;
import io.proleap.cobol.asg.metamodel.procedure.send.SendStatement;
import io.proleap.cobol.asg.metamodel.procedure.send.Sync;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class SendStatementImpl extends StatementImpl implements SendStatement {

	protected Async async;

	protected final SendStatementContext ctx;

	protected NotOnExceptionClause notOnExceptionClause;

	protected OnExceptionClause onExceptionClause;

	protected SendType sendType;

	protected final StatementType statementType = StatementTypeEnum.SEND;

	protected Sync sync;

	public SendStatementImpl(final ProgramUnit programUnit, final Scope scope, final SendStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Async addAsync(final SendStatementAsyncContext ctx) {
		Async result = (Async) getASGElement(ctx);

		if (result == null) {
			result = new AsyncImpl(programUnit, ctx);

			// type
			final Async.AsyncType type;

			if (ctx.BOTTOM() != null) {
				type = Async.AsyncType.BOTTOM;
			} else if (ctx.TOP() != null) {
				type = Async.AsyncType.TOP;
			} else {
				type = null;
			}

			result.setAsyncType(type);

			// call
			if (ctx.identifier() != null) {
				final Call dateDescriptionEntryCall = createCall(ctx.identifier());
				result.setDataDescriptionEntryCall(dateDescriptionEntryCall);
			}

			async = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Sync addSync(final SendStatementSyncContext ctx) {
		Sync result = (Sync) getASGElement(ctx);

		if (result == null) {
			result = new SyncImpl(programUnit, ctx);

			// receiving program
			final ValueStmt receivingProgramValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setReceivingProgramValueStmt(receivingProgramValueStmt);

			// from
			if (ctx.sendFromPhrase() != null) {
				result.addFrom(ctx.sendFromPhrase());
			}

			// with
			if (ctx.sendWithPhrase() != null) {
				result.addWith(ctx.sendWithPhrase());
			}

			// replacing
			if (ctx.sendReplacingPhrase() != null) {
				result.setReplacing(true);
			}

			// advancing
			if (ctx.sendAdvancingPhrase() != null) {
				result.addAdvancing(ctx.sendAdvancingPhrase());
			}

			sync = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Async getAsync() {
		return async;
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
	public SendType getSendType() {
		return sendType;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public Sync getSync() {
		return sync;
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
	public void setSendType(final SendType sendType) {
		this.sendType = sendType;
	}

}
