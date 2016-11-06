/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.PerformProcedureStatementContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.PerformProcedureStatement;
import io.proleap.cobol.parser.metamodel.call.Call;

public class PerformProcedureStatementImpl extends CobolScopedElementImpl implements PerformProcedureStatement {

	protected final List<Call> calls = new ArrayList<Call>();

	protected final PerformProcedureStatementContext ctx;

	public PerformProcedureStatementImpl(final CopyBook copyBook, final CobolScope superScope,
			final PerformProcedureStatementContext ctx) {
		super(copyBook, superScope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addCall(final Call call) {
		calls.add(call);
	}

	@Override
	public void addCalls(final List<Call> calls) {
		this.calls.addAll(calls);
	}

	@Override
	public List<Call> getCalls() {
		return calls;
	}

}
