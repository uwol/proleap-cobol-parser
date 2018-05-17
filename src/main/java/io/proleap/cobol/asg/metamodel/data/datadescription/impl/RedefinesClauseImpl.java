/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.CobolParser.DataRedefinesClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.datadescription.RedefinesClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RedefinesClauseImpl extends CobolDivisionElementImpl implements RedefinesClause {

	protected DataRedefinesClauseContext ctx;

	protected Call redefinesCall;

	public RedefinesClauseImpl(final ProgramUnit programUnit, final DataRedefinesClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getRedefinesCall() {
		return redefinesCall;
	}

	@Override
	public void setRedefinesCall(final Call redefinesCall) {
		this.redefinesCall = redefinesCall;
	}

}
