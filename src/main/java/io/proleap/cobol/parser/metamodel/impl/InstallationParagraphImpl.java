/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import io.proleap.cobol.Cobol85Parser.InstallationParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.InstallationParagraph;

public class InstallationParagraphImpl extends IdentificationDivisionBodyImpl implements InstallationParagraph {

	protected final InstallationParagraphContext ctx;

	public InstallationParagraphImpl(final CopyBook copyBook, final CobolScope superScope,
			final InstallationParagraphContext ctx) {
		super(copyBook, superScope, ctx);

		this.ctx = ctx;
	}

}
