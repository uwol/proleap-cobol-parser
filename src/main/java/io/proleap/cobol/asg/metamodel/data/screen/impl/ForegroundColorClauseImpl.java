/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionForegroundColorClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.ForegroundColorClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ForegroundColorClauseImpl extends CobolDivisionElementImpl implements ForegroundColorClause {

	protected ValueStmt colorValueStmt;

	protected ScreenDescriptionForegroundColorClauseContext ctx;

	public ForegroundColorClauseImpl(final ProgramUnit programUnit,
			final ScreenDescriptionForegroundColorClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getColorValueStmt() {
		return colorValueStmt;
	}

	@Override
	public void setColorValueStmt(final ValueStmt colorValueStmt) {
		this.colorValueStmt = colorValueStmt;
	}

}
