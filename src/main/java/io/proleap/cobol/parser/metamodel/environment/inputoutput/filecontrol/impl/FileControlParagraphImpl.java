/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.FileControlParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class FileControlParagraphImpl extends CobolDivisionElementImpl implements FileControlParagraph {

	protected final FileControlParagraphContext ctx;

	protected final List<FileControlEntry> fileControlEntries = new ArrayList<FileControlEntry>();

	public FileControlParagraphImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final FileControlParagraphContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addFileControlEntry(final FileControlEntry fileControlEntry) {
		fileControlEntries.add(fileControlEntry);
	}

	@Override
	public List<FileControlEntry> getFileControlEntries() {
		return fileControlEntries;
	}

}
