/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.identification.impl;

import io.proleap.cobol.CobolParser.DateWrittenParagraphContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.identification.DateWrittenParagraph;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class DateWrittenParagraphImpl extends CobolDivisionElementImpl implements DateWrittenParagraph {

	protected final DateWrittenParagraphContext ctx;

	protected String dateWritten;

	public DateWrittenParagraphImpl(final ProgramUnit programUnit, final DateWrittenParagraphContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public String getDateWritten() {
		return dateWritten;
	}

	@Override
	public void setDateWritten(final String dateWritten) {
		this.dateWritten = dateWritten;
	}
}
