/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import io.proleap.cobol.CobolParser.ProcedureDivisionByReferenceContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.ByReference;

public class ByReferenceImpl extends CobolDivisionElementImpl implements ByReference {

	protected boolean any;

	protected final ProcedureDivisionByReferenceContext ctx;

	protected boolean optional;

	protected Call referenceCall;

	public ByReferenceImpl(final ProgramUnit programUnit, final ProcedureDivisionByReferenceContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getReferenceCall() {
		return referenceCall;
	}

	@Override
	public boolean isAny() {
		return any;
	}

	@Override
	public boolean isOptional() {
		return optional;
	}

	@Override
	public void setAny(final boolean any) {
		this.any = any;
	}

	@Override
	public void setOptional(final boolean optional) {
		this.optional = optional;
	}

	@Override
	public void setReferenceCall(final Call fileCall) {
		referenceCall = fileCall;
	}
}
