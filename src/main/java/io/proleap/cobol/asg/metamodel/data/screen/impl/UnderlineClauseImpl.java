/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionUnderlineClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.UnderlineClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class UnderlineClauseImpl extends CobolDivisionElementImpl implements UnderlineClause {

	protected ScreenDescriptionUnderlineClauseContext ctx;

	protected boolean underline;

	public UnderlineClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionUnderlineClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isUnderline() {
		return underline;
	}

	@Override
	public void setUnderline(final boolean underline) {
		this.underline = underline;
	}

}
