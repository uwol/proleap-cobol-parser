/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.identification.impl;

import io.proleap.cobol.CobolParser.AuthorParagraphContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.identification.AuthorParagraph;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class AuthorParagraphImpl extends CobolDivisionElementImpl implements AuthorParagraph {

	protected String author;

	protected final AuthorParagraphContext ctx;

	public AuthorParagraphImpl(final ProgramUnit programUnit, final AuthorParagraphContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public void setAuthor(final String author) {
		this.author = author;
	}
}
