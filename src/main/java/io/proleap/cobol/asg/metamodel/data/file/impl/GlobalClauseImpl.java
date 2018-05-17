/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file.impl;

import io.proleap.cobol.CobolParser.GlobalClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.file.GlobalClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class GlobalClauseImpl extends CobolDivisionElementImpl implements GlobalClause {

	protected GlobalClauseContext ctx;

	protected boolean global;

	public GlobalClauseImpl(final ProgramUnit programUnit, final GlobalClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isGlobal() {
		return global;
	}

	@Override
	public void setGlobal(final boolean global) {
		this.global = global;
	}

}
