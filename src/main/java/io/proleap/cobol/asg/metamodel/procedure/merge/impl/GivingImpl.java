/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.merge.impl;

import io.proleap.cobol.CobolParser.MergeGivingContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.merge.Giving;

public class GivingImpl extends CobolDivisionElementImpl implements Giving {

	protected CloseProcedure closeProcedure;

	protected final MergeGivingContext ctx;

	protected Call fileCall;

	public GivingImpl(final ProgramUnit programUnit, final MergeGivingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CloseProcedure getCloseProcedure() {
		return closeProcedure;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public void setCloseProcedure(final CloseProcedure closeProcedure) {
		this.closeProcedure = closeProcedure;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

}
