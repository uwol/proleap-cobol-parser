/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.DataRenamesClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.datadescription.RenamesClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RenamesClauseImpl extends CobolDivisionElementImpl implements RenamesClause {

	protected List<Call> calls = new ArrayList<Call>();

	protected DataRenamesClauseContext ctx;

	protected Call from;

	protected Call to;

	public RenamesClauseImpl(final ProgramUnit programUnit, final DataRenamesClauseContext ctx) {
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

	@Override
	public Call getFrom() {
		return from;
	}

	@Override
	public Call getTo() {
		return to;
	}

	@Override
	public void setFrom(final Call from) {
		this.from = from;
	}

	@Override
	public void setTo(final Call to) {
		this.to = to;
	}

}
