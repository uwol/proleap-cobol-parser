/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionEraseClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.EraseClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class EraseClauseImpl extends CobolDivisionElementImpl implements EraseClause {

	protected ScreenDescriptionEraseClauseContext ctx;

	protected Type type;

	public EraseClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionEraseClauseContext ctx) {
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
