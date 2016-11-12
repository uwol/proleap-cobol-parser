/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.InputOutputSectionContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.FileControlParagraph;
import io.proleap.cobol.parser.metamodel.environment.InputOutputSection;
import io.proleap.cobol.parser.metamodel.environment.IoControlParagraph;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class InputOutputSectionImpl extends CobolDivisionElementImpl implements InputOutputSection {

	protected final InputOutputSectionContext ctx;

	protected List<FileControlParagraph> fileControlParagraphs = new ArrayList<FileControlParagraph>();

	protected List<IoControlParagraph> ioControlParagraphs = new ArrayList<IoControlParagraph>();

	public InputOutputSectionImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final InputOutputSectionContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addFileControlParagraph(final FileControlParagraph fileControlParagraph) {
		fileControlParagraphs.add(fileControlParagraph);
	}

	@Override
	public void addIoControlParagraph(final IoControlParagraph ioControlParagraph) {
		ioControlParagraphs.add(ioControlParagraph);
	}

	@Override
	public List<FileControlParagraph> getFileControlParagraphs() {
		return fileControlParagraphs;
	}

	@Override
	public List<IoControlParagraph> getIoControlParagraphs() {
		return ioControlParagraphs;
	}

}
