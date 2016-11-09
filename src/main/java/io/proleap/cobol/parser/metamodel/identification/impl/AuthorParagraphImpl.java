/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.identification.impl;

import io.proleap.cobol.Cobol85Parser.AuthorParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.identification.AuthorParagraph;

public class AuthorParagraphImpl extends IdentificationDivisionBodyImpl implements AuthorParagraph {

	protected final AuthorParagraphContext ctx;

	public AuthorParagraphImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final AuthorParagraphContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

}
