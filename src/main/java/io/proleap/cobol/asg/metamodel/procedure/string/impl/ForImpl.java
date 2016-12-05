/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.string.impl;

import io.proleap.cobol.Cobol85Parser.StringForPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.string.For;

public class ForImpl extends CobolDivisionElementImpl implements For {

	protected final StringForPhraseContext ctx;

	protected Call forCall;

	public ForImpl(final ProgramUnit programUnit, final StringForPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getForCall() {
		return forCall;
	}

	@Override
	public void setForCall(final Call forCall) {
		this.forCall = forCall;
	}

}
