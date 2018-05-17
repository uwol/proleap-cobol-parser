/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file.impl;

import io.proleap.cobol.CobolParser.RecordContainsClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.file.RecordContainsClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RecordContainsClauseImpl extends CobolDivisionElementImpl implements RecordContainsClause {

	protected final RecordContainsClauseContext ctx;

	protected Call dependingOnCall;

	protected IntegerLiteral from;

	protected IntegerLiteral to;

	protected boolean varying;

	public RecordContainsClauseImpl(final ProgramUnit programUnit, final RecordContainsClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDependingOnCall() {
		return dependingOnCall;
	}

	@Override
	public IntegerLiteral getFrom() {
		return from;
	}

	@Override
	public IntegerLiteral getTo() {
		return to;
	}

	@Override
	public boolean isVarying() {
		return varying;
	}

	@Override
	public void setDependingOnCall(final Call dependingOnCall) {
		this.dependingOnCall = dependingOnCall;
	}

	@Override
	public void setFrom(final IntegerLiteral from) {
		this.from = from;
	}

	@Override
	public void setTo(final IntegerLiteral to) {
		this.to = to;
	}

	@Override
	public void setVarying(final boolean varying) {
		this.varying = varying;
	}

}
