/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort.impl;

import io.proleap.cobol.Cobol85Parser.SortInputThroughContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.InputThrough;

public class InputThroughImpl extends CobolDivisionElementImpl implements InputThrough {

	protected final SortInputThroughContext ctx;

	protected Call procedureCall;

	public InputThroughImpl(final ProgramUnit programUnit, final SortInputThroughContext ctx) {
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
