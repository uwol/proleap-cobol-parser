/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.entry.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.EntryStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.entry.EntryStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class EntryStatementImpl extends StatementImpl implements EntryStatement {

	protected final EntryStatementContext ctx;

	protected ValueStmt entryValueStmt;

	protected final StatementType statementType = StatementTypeEnum.ENTRY;

	protected List<Call> usingCalls = new ArrayList<Call>();

	public EntryStatementImpl(final ProgramUnit programUnit, final Scope scope, final EntryStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addUsingCall(final Call usingCall) {
		usingCalls.add(usingCall);
	}

	@Override
	public ValueStmt getEntryValueStmt() {
		return entryValueStmt;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public List<Call> getUsingCalls() {
		return usingCalls;
	}

	@Override
	public void setEntryValueStmt(final ValueStmt entryValueStmt) {
		this.entryValueStmt = entryValueStmt;
	}

}
