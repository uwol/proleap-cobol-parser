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
import io.proleap.cobol.asg.metamodel.data.screen.SizeClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class SizeClauseImpl extends CobolDivisionElementImpl implements SizeClause {

	protected ScreenDescriptionSizeClauseContext ctx;

	protected ValueStmt sizeValueStmt;

	public SizeClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionSizeClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getSizeValueStmt() {
		return sizeValueStmt;
	}

	@Override
	public void setSizeValueStmt(final ValueStmt sizeValueStmt) {
		this.sizeValueStmt = sizeValueStmt;
	}

}
