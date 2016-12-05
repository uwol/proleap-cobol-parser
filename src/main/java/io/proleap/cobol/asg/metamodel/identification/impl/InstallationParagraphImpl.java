/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.identification.impl;

import io.proleap.cobol.Cobol85Parser.InstallationParagraphContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.identification.InstallationParagraph;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class InstallationParagraphImpl extends CobolDivisionElementImpl implements InstallationParagraph {

	protected final InstallationParagraphContext ctx;

	public InstallationParagraphImpl(final ProgramUnit programUnit, final InstallationParagraphContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
