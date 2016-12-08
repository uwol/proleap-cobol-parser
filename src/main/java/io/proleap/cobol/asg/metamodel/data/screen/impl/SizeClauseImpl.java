/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionSizeClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.screen.SizeClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class SizeClauseImpl extends CobolDivisionElementImpl implements SizeClause {

	protected ScreenDescriptionSizeClauseContext ctx;

	protected Call sizeCall;

	public SizeClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionSizeClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getSizeCall() {
		return sizeCall;
	}

	@Override
	public void setSizeCall(final Call sizeCall) {
		this.sizeCall = sizeCall;
	}

}
