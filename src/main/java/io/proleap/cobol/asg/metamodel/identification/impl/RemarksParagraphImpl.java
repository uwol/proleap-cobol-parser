/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.identification.impl;

import io.proleap.cobol.Cobol85Parser.RemarksParagraphContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.identification.RemarksParagraph;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RemarksParagraphImpl extends CobolDivisionElementImpl implements RemarksParagraph {

	protected final RemarksParagraphContext ctx;

	public RemarksParagraphImpl(final ProgramUnit programUnit, final RemarksParagraphContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
