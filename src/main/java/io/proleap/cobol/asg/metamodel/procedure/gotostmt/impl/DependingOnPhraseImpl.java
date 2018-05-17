/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.gotostmt.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.GoToDependingOnStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.DependingOnPhrase;

public class DependingOnPhraseImpl extends CobolDivisionElementImpl implements DependingOnPhrase {

	protected final GoToDependingOnStatementContext ctx;

	protected Call dependingOnCall;

	protected boolean moreLabels;

	protected List<Call> procedureCalls = new ArrayList<Call>();

	public DependingOnPhraseImpl(final ProgramUnit programUnit, final GoToDependingOnStatementContext ctx) {
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
