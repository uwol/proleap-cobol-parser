/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import io.proleap.cobol.Cobol85Parser.ObjectComputerParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.ObjectComputerParagraph;

public class ObjectComputerParagraphImpl extends ConfigurationSectionParagraphImpl implements ObjectComputerParagraph {

	protected final ObjectComputerParagraphContext ctx;

	protected String name;

	public ObjectComputerParagraphImpl(final String name, final ProgramUnit programUnit, final CobolDivision scope,
			final ObjectComputerParagraphContext ctx) {
		super(programUnit, scope, ctx);

		this.name = name;
		this.ctx = ctx;
	}

	@Override
	public String getName() {
		return name;
	}

}
