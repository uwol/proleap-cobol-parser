/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import io.proleap.cobol.Cobol85Parser.AuthorParagraphContext;
import io.proleap.cobol.parser.metamodel.AuthorParagraph;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;

public class AuthorParagraphImpl extends IdentificationDivisionBodyImpl implements AuthorParagraph {

	protected final AuthorParagraphContext ctx;

	public AuthorParagraphImpl(final CopyBook copyBook, final CobolScope superScope, final AuthorParagraphContext ctx) {
		super(copyBook, superScope, ctx);

		this.ctx = ctx;
	}

}
