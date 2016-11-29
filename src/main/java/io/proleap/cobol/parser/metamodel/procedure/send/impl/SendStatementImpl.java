/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.send.impl;

import io.proleap.cobol.Cobol85Parser.SendStatementAsyncContext;
import io.proleap.cobol.Cobol85Parser.SendStatementContext;
import io.proleap.cobol.Cobol85Parser.SendStatementSyncContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.Scope;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.NotOnException;
import io.proleap.cobol.parser.metamodel.procedure.OnException;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.send.Async;
import io.proleap.cobol.parser.metamodel.procedure.send.SendStatement;
import io.proleap.cobol.parser.metamodel.procedure.send.Sync;

public class SendStatementImpl extends StatementImpl implements SendStatement {

	protected Async async;

	protected final SendStatementContext ctx;

	protected NotOnException notOnException;

	protected OnException onException;

	protected Sync sync;

	protected Type type;

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
			final Async.Type type;

			if (ctx.BOTTOM() != null) {
				type = Async.Type.Bottom;
			} else if (ctx.TOP() != null) {
				type = Async.Type.Top;
			} else {
				type = null;
			}

			result.setType(type);

			// call
			final Call dateDescriptionEntryCall = createCall(ctx.identifier());
			result.setDataDescriptionEntryCall(dateDescriptionEntryCall);

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

			// receiving program call
			final Call receivingProgramCall = createCall(ctx.identifier(), ctx.literal());
			result.setReceivingProgramCall(receivingProgramCall);

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
	public NotOnException getNotOnException() {
		return notOnException;
	}

	@Override
	public OnException getOnException() {
		return onException;
	}

	@Override
	public Sync getSync() {
		return sync;
	}

	@Override
	public Type getType() {
		return type;
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
	public void setType(final Type type) {
		this.type = type;
	}

}
