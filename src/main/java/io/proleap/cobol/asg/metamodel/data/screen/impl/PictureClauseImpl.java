/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionPictureClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.PictureClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class PictureClauseImpl extends CobolDivisionElementImpl implements PictureClause {

	protected ScreenDescriptionPictureClauseContext ctx;

	protected String pictureString;

	public PictureClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionPictureClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public String getPictureString() {
		return pictureString;
	}

	@Override
	public void setPictureString(final String pictureString) {
		this.pictureString = pictureString;
	}

}
