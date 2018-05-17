/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.identification.impl;

import io.proleap.cobol.CobolParser.ProgramIdParagraphContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.identification.ProgramIdParagraph;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ProgramIdParagraphImpl extends CobolDivisionElementImpl implements ProgramIdParagraph {

	protected Attribute attribute;

	protected final ProgramIdParagraphContext ctx;

	protected String name;

	public ProgramIdParagraphImpl(final String name, final ProgramUnit programUnit,
			final ProgramIdParagraphContext ctx) {
		super(programUnit, ctx);

		this.name = name;
		this.ctx = ctx;
	}

	@Override
	public Attribute getAttribute() {
		return attribute;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setAttribute(final Attribute attribute) {
		this.attribute = attribute;
	}
}
