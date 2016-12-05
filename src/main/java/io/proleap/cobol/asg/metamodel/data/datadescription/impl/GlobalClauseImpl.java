/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataGlobalClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.GlobalClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class GlobalClauseImpl extends CobolDivisionElementImpl implements GlobalClause {

	protected DataGlobalClauseContext ctx;

	protected boolean global;

	public GlobalClauseImpl(final ProgramUnit programUnit, final DataGlobalClauseContext ctx) {
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
