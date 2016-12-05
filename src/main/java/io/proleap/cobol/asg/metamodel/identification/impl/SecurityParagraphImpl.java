/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.identification.impl;

import io.proleap.cobol.Cobol85Parser.SecurityParagraphContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.identification.SecurityParagraph;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class SecurityParagraphImpl extends CobolDivisionElementImpl implements SecurityParagraph {

	protected final SecurityParagraphContext ctx;

	public SecurityParagraphImpl(final ProgramUnit programUnit, final SecurityParagraphContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
