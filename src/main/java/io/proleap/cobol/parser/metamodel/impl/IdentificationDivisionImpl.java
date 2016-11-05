/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import io.proleap.cobol.Cobol85Parser.IdentificationDivisionContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.IdentificationDivision;
import io.proleap.cobol.parser.metamodel.ProgramIdParagraph;

public class IdentificationDivisionImpl extends CobolScopedElementImpl implements IdentificationDivision {

	protected final IdentificationDivisionContext ctx;

	protected ProgramIdParagraph programIdParagraph;

	public IdentificationDivisionImpl(final CopyBook copyBook, final CobolScope superScope,
			final IdentificationDivisionContext ctx) {
		super(copyBook, superScope, ctx);

		this.ctx = ctx;
	}

	@Override
	public ProgramIdParagraph getProgramIdParagraph() {
		return programIdParagraph;
	}

	@Override
	public void setProgramIdParagraph(final ProgramIdParagraph programIdParagraph) {
		this.programIdParagraph = programIdParagraph;
	}

}
