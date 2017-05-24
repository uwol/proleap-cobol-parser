/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call.impl;

import io.proleap.cobol.Cobol85Parser.CallByReferenceContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.ByReference;

public class ByReferenceImpl extends CobolDivisionElementImpl implements ByReference {

	protected ByReferenceType byReferenceType;

	protected Call call;

	protected final CallByReferenceContext ctx;

	public ByReferenceImpl(final ProgramUnit programUnit, final CallByReferenceContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByReferenceType getByReferenceType() {
		return byReferenceType;
	}

	@Override
	public Call getCall() {
		return call;
	}

	@Override
	public void setByReferenceType(final ByReferenceType byReferenceType) {
		this.byReferenceType = byReferenceType;
	}

	@Override
	public void setCall(final Call call) {
		this.call = call;
	}

}
