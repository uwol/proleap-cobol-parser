/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort.impl;

import io.proleap.cobol.CobolParser.SortOutputThroughContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.OutputThrough;

public class OutputThroughImpl extends CobolDivisionElementImpl implements OutputThrough {

	protected final SortOutputThroughContext ctx;

	protected Call procedureCall;

	public OutputThroughImpl(final ProgramUnit programUnit, final SortOutputThroughContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getProcedureCall() {
		return procedureCall;
	}

	@Override
	public void setProcedureCall(final Call procedureCall) {
		this.procedureCall = procedureCall;
	}

}
