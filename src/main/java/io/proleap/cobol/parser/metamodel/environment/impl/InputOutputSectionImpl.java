/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import io.proleap.cobol.Cobol85Parser.InputOutputSectionContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.FileControlParagraph;
import io.proleap.cobol.parser.metamodel.environment.InputOutputSection;
import io.proleap.cobol.parser.metamodel.environment.IoControlParagraph;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class InputOutputSectionImpl extends CobolDivisionElementImpl implements InputOutputSection {

	protected final InputOutputSectionContext ctx;

	protected FileControlParagraph fileControlParagraph;

	protected IoControlParagraph ioControlParagraph;

	public InputOutputSectionImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final InputOutputSectionContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public FileControlParagraph getFileControlParagraph() {
		return fileControlParagraph;
	}

	@Override
	public IoControlParagraph getIoControlParagraph() {
		return ioControlParagraph;
	}

	@Override
	public void setFileControlParagraph(final FileControlParagraph fileControlParagraph) {
		this.fileControlParagraph = fileControlParagraph;
	}

	@Override
	public void setIoControlParagraph(final IoControlParagraph ioControlParagraph) {
		this.ioControlParagraph = ioControlParagraph;
	}

}
