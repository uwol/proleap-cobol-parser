/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionReverseVideoClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.ReverseVideoClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ReverseVideoClauseImpl extends CobolDivisionElementImpl implements ReverseVideoClause {

	protected ScreenDescriptionReverseVideoClauseContext ctx;

	protected boolean reverseVideo;

	public ReverseVideoClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionReverseVideoClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isReverseVideo() {
		return reverseVideo;
	}

	@Override
	public void setReverseVideo(final boolean reverseVideo) {
		this.reverseVideo = reverseVideo;
	}

}
