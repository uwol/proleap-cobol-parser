/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.identification.impl;

import io.proleap.cobol.Cobol85Parser.DateCompiledParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.identification.DateCompiledParagraph;

public class DateCompiledParagraphImpl extends IdentificationDivisionBodyImpl implements DateCompiledParagraph {

	protected final DateCompiledParagraphContext ctx;

	public DateCompiledParagraphImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final DateCompiledParagraphContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

}
