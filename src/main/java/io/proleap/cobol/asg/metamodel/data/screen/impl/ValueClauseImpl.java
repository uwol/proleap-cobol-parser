/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionValueClauseContext;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.ValueClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ValueClauseImpl extends CobolDivisionElementImpl implements ValueClause {

	protected ScreenDescriptionValueClauseContext ctx;

	protected Literal literal;

	public ValueClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionValueClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Literal getLiteral() {
		return literal;
	}

	@Override
	public void setLiteral(final Literal literal) {
		this.literal = literal;
	}

}
