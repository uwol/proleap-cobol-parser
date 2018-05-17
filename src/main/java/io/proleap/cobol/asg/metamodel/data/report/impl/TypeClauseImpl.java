/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.report.impl;

import io.proleap.cobol.CobolParser.ReportGroupTypeClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.report.TypeClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class TypeClauseImpl extends CobolDivisionElementImpl implements TypeClause {

	protected ReportGroupTypeClauseContext ctx;

	protected Call dataCall;

	protected TypeClauseType typeClauseType;

	public TypeClauseImpl(final ProgramUnit programUnit, final ReportGroupTypeClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getDataCall() {
		return dataCall;
	}

	@Override
	public TypeClauseType getTypeClauseType() {
		return typeClauseType;
	}

	@Override
	public void setDataCall(final Call dataCall) {
		this.dataCall = dataCall;
	}

	@Override
	public void setTypeClauseType(final TypeClauseType typeClauseType) {
		this.typeClauseType = typeClauseType;
	}

}
