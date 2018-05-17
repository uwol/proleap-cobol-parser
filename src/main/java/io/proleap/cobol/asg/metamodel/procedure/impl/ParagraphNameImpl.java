/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import io.proleap.cobol.CobolParser.ParagraphNameContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.ParagraphName;

public class ParagraphNameImpl extends CobolDivisionElementImpl implements ParagraphName {

	protected final ParagraphNameContext ctx;

	protected final String name;

	public ParagraphNameImpl(final String name, final ProgramUnit programUnit, final ParagraphNameContext ctx) {
		super(programUnit, ctx);

		this.name = name;
		this.ctx = ctx;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}

}
