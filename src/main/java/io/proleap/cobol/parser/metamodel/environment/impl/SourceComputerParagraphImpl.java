/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import io.proleap.cobol.Cobol85Parser.SourceComputerParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.SourceComputerParagraph;

public class SourceComputerParagraphImpl extends ConfigurationSectionParagraphImpl implements SourceComputerParagraph {

	protected final SourceComputerParagraphContext ctx;

	protected boolean debuggingMode;

	protected String name;

	public SourceComputerParagraphImpl(final String name, final ProgramUnit programUnit, final CobolDivision scope,
			final SourceComputerParagraphContext ctx) {
		super(programUnit, scope, ctx);

		this.name = name;
		this.ctx = ctx;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isDebuggingMode() {
		return debuggingMode;
	}

	@Override
	public void setDebuggingMode(final boolean debuggingMode) {
		this.debuggingMode = debuggingMode;
	}

}
