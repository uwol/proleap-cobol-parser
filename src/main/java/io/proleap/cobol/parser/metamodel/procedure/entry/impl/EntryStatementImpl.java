/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.entry.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.EntryStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.Scope;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.entry.EntryStatement;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class EntryStatementImpl extends StatementImpl implements EntryStatement {

	protected final EntryStatementContext ctx;

	protected Call entryCall;

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
	public Call getEntryCall() {
		return entryCall;
	}

	@Override
	public List<Call> getUsingCalls() {
		return usingCalls;
	}

	@Override
	public void setEntryCall(final Call entryCall) {
		this.entryCall = entryCall;
	}

}
