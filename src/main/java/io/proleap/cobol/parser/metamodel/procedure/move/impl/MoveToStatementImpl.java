/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.move.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementSendingAreaContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.Scope;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.move.MoveToStatement;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.ValueStmtDelegateImpl;

public class MoveToStatementImpl extends StatementImpl implements MoveToStatement {

	protected final MoveToStatementContext ctx;

	protected List<Call> receivingAreaCalls = new ArrayList<Call>();

	protected ValueStmt sendingAreaValueStmt;

	public MoveToStatementImpl(final ProgramUnit programUnit, final Scope scope, final MoveToStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addReceivingAreaCall(final Call receivingAreaCall) {
		receivingAreaCalls.add(receivingAreaCall);
	}

	@Override
	public ValueStmt addSendingAreaValueStmt(final MoveToStatementSendingAreaContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			/*
			 * then the delegated value stmt
			 */
			final ValueStmt delegatedValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result = new ValueStmtDelegateImpl(delegatedValueStmt, programUnit, ctx);

			sendingAreaValueStmt = result;
			registerASGElement(result);
		}

		return result;
	}

}
