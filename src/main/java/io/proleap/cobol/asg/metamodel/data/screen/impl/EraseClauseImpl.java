/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionEraseClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.EraseClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class EraseClauseImpl extends CobolDivisionElementImpl implements EraseClause {

	protected ScreenDescriptionEraseClauseContext ctx;

	protected EraseClauseType eraseClauseType;

	public EraseClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionEraseClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public EraseClauseType getEraseClauseType() {
		return eraseClauseType;
	}

	@Override
	public void setEraseClauseType(final EraseClauseType eraseClauseType) {
		this.eraseClauseType = eraseClauseType;
	}

}
