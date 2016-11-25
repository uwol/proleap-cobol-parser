/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.gotostmt.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.GoToDependingOnStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.gotostmt.DependingOn;

public class DependingOnImpl extends CobolDivisionElementImpl implements DependingOn {

	protected final GoToDependingOnStatementContext ctx;

	protected Call dependingOnCall;

	protected boolean moreLabels;

	protected List<Call> procedureCalls = new ArrayList<Call>();

	public DependingOnImpl(final ProgramUnit programUnit, final GoToDependingOnStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addProcedureCall(final Call procedureCall) {
		procedureCalls.add(procedureCall);
	}

	@Override
	public Call getDependingOnCall() {
		return dependingOnCall;
	}

	@Override
	public List<Call> getProcedureCalls() {
		return procedureCalls;
	}

	@Override
	public boolean isMoreLabels() {
		return moreLabels;
	}

	@Override
	public void setDependingOnCall(final Call dependingOnCall) {
		this.dependingOnCall = dependingOnCall;
	}

	@Override
	public void setMoreLabels(final boolean moreLabels) {
		this.moreLabels = moreLabels;
	}

}
