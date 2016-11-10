/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.InputOutputSectionContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.InputOutputSection;
import io.proleap.cobol.parser.metamodel.environment.InputOutputSectionParagraph;

public class InputOutputSectionImpl extends EnvironmentDivisionBodyImpl implements InputOutputSection {

	protected final InputOutputSectionContext ctx;

	protected List<InputOutputSectionParagraph> inputOutputSectionParagraphs;

	public InputOutputSectionImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final InputOutputSectionContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addInputOutputSectionParagraph(final InputOutputSectionParagraph inputOutputSectionParagraph) {
		inputOutputSectionParagraphs.add(inputOutputSectionParagraph);
	}

	@Override
	public List<InputOutputSectionParagraph> getInputOutputSectionParagraphs() {
		return inputOutputSectionParagraphs;
	}

}
