/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.identification.impl;

import io.proleap.cobol.CobolParser.RemarksParagraphContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.identification.RemarksParagraph;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RemarksParagraphImpl extends CobolDivisionElementImpl implements RemarksParagraph {

	protected final RemarksParagraphContext ctx;

	protected String remarks;

	public RemarksParagraphImpl(final ProgramUnit programUnit, final RemarksParagraphContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public String getRemarks() {
		return remarks;
	}

	@Override
	public void setRemarks(final String remarks) {
		this.remarks = remarks;
	}
}
