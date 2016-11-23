/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.cancel.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.CancelCallContext;
import io.proleap.cobol.Cobol85Parser.CancelStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.cancel.CancelCall;
import io.proleap.cobol.parser.metamodel.procedure.cancel.CancelStatement;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class CancelStatementImpl extends StatementImpl implements CancelStatement {

	private final static Logger LOG = LogManager.getLogger(CancelStatementImpl.class);

	protected List<CancelCall> cancelCalls = new ArrayList<CancelCall>();

	protected final CancelStatementContext ctx;

	public CancelStatementImpl(final ProgramUnit programUnit, final CancelStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CancelCall addCancelCall(final CancelCallContext ctx) {
		CancelCall result = (CancelCall) getASGElement(ctx);

		if (result == null) {
			result = new CancelCallImpl(programUnit, ctx);

			// call
			final Call call;

			if (ctx.identifier() != null) {
				call = createCall(ctx.identifier());
			} else if (ctx.literal() != null) {
				call = createCall(ctx.literal());
			} else if (ctx.libraryName() != null) {
				call = createCall(ctx.libraryName());
			} else {
				LOG.warn("unknown call at {}", ctx);
				call = null;
			}

			result.setCall(call);

			// type
			final CancelCall.Type type;

			if (ctx.BYTITLE() != null) {
				type = CancelCall.Type.ByTitle;
			} else if (ctx.BYFUNCTION() != null) {
				type = CancelCall.Type.ByFunction;
			} else {
				type = null;
			}

			result.setType(type);

			cancelCalls.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<CancelCall> getCancelCalls() {
		return cancelCalls;
	}

}
