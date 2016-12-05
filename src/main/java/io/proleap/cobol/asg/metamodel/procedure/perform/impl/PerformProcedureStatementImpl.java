/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.perform.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.PerformProcedureStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformProcedureStatement;

public class PerformProcedureStatementImpl extends CobolDivisionElementImpl implements PerformProcedureStatement {

	protected final List<Call> calls = new ArrayList<Call>();

	protected final PerformProcedureStatementContext ctx;

	public PerformProcedureStatementImpl(final ProgramUnit programUnit, final PerformProcedureStatementContext ctx) {
		super(programUnit, ctx);

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
