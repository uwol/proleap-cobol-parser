/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.alter.impl;

import io.proleap.cobol.Cobol85Parser.AlterProceedToContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.alter.AlterProceedTo;

public class AlterProceedToImpl extends CobolDivisionElementImpl implements AlterProceedTo {

	protected final AlterProceedToContext ctx;

	protected Call sourceCall;

	protected Call targetCall;

	public AlterProceedToImpl(final ProgramUnit programUnit, final AlterProceedToContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getSourceCall() {
		return sourceCall;
	}

	@Override
	public Call getTargetCall() {
		return targetCall;
	}

	@Override
	public void setSourceCall(final Call sourceCall) {
		this.sourceCall = sourceCall;
	}

	@Override
	public void setTargetCall(final Call targetCall) {
		this.targetCall = targetCall;
	}

}
