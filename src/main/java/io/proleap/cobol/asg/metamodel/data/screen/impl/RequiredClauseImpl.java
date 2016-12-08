/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionRequiredClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.RequiredClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RequiredClauseImpl extends CobolDivisionElementImpl implements RequiredClause {

	protected ScreenDescriptionRequiredClauseContext ctx;

	protected Type type;

	public RequiredClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionRequiredClauseContext ctx) {
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
