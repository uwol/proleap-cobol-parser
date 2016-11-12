/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.identification.impl;

import io.proleap.cobol.Cobol85Parser.RemarksParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.identification.RemarksParagraph;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class RemarksParagraphImpl extends CobolDivisionElementImpl implements RemarksParagraph {

	protected final RemarksParagraphContext ctx;

	public RemarksParagraphImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final RemarksParagraphContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

}
