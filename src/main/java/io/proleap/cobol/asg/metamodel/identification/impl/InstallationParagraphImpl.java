/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.identification.impl;

import io.proleap.cobol.CobolParser.InstallationParagraphContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.identification.InstallationParagraph;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class InstallationParagraphImpl extends CobolDivisionElementImpl implements InstallationParagraph {

	protected final InstallationParagraphContext ctx;

	protected String installation;

	public InstallationParagraphImpl(final ProgramUnit programUnit, final InstallationParagraphContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public String getInstallation() {
		return installation;
	}

	@Override
	public void setInstallation(final String installation) {
		this.installation = installation;
	}
}
