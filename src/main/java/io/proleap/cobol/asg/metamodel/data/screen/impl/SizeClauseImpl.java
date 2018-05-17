/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionSizeClauseContext;
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
