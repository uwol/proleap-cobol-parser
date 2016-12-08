/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionGridClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.GridClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class GridClauseImpl extends CobolDivisionElementImpl implements GridClause {

	protected ScreenDescriptionGridClauseContext ctx;

	protected Type type;

	public GridClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionGridClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
