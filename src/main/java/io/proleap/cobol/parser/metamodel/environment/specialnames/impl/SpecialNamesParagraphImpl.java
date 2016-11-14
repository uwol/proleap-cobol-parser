/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.specialnames.impl;

import io.proleap.cobol.Cobol85Parser.SpecialNamesParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.specialnames.SpecialNamesParagraph;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

// FIXME: add specialNameClauses
public class SpecialNamesParagraphImpl extends CobolDivisionElementImpl implements SpecialNamesParagraph {

	protected final SpecialNamesParagraphContext ctx;

	public SpecialNamesParagraphImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final SpecialNamesParagraphContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

}
