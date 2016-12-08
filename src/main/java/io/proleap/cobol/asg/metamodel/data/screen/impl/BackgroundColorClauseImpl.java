/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionBackgroundColorClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.screen.BackgroundColorClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class BackgroundColorClauseImpl extends CobolDivisionElementImpl implements BackgroundColorClause {

	protected Call colorCall;

	protected ScreenDescriptionBackgroundColorClauseContext ctx;

	public BackgroundColorClauseImpl(final ProgramUnit programUnit,
			final ScreenDescriptionBackgroundColorClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getColorCall() {
		return colorCall;
	}

	@Override
	public void setColorCall(final Call colorCall) {
		this.colorCall = colorCall;
	}

}
