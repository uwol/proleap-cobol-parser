/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.cancel.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.CancelCallContext;
import io.proleap.cobol.CobolParser.CancelStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.cancel.CancelCall;
import io.proleap.cobol.asg.metamodel.procedure.cancel.CancelStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class CancelStatementImpl extends StatementImpl implements CancelStatement {

	protected List<CancelCall> cancelCalls = new ArrayList<CancelCall>();

	protected final CancelStatementContext ctx;

	protected final StatementType statementType = StatementTypeEnum.CANCEL;

	public CancelStatementImpl(final ProgramUnit programUnit, final Scope scope, final CancelStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public CancelCall addCancelCall(final CancelCallContext ctx) {
		CancelCall result = (CancelCall) getASGElement(ctx);

		if (result == null) {
			result = new CancelCallImpl(programUnit, ctx);

			// call
			final ValueStmt valueStmt = createValueStmt(ctx.identifier(), ctx.literal(), ctx.libraryName());
			result.setValueStmt(valueStmt);

			// type
			final CancelCall.CancelType type;

			if (ctx.BYTITLE() != null) {
				type = CancelCall.CancelType.BY_TITLE;
			} else if (ctx.BYFUNCTION() != null) {
				type = CancelCall.CancelType.BY_FUNCTION;
			} else {
				type = null;
			}

			result.setCancelType(type);

			cancelCalls.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<CancelCall> getCancelCalls() {
		return cancelCalls;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}
}
