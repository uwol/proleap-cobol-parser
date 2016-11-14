/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.FileSectionContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.FileDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.FileSection;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class FileSectionImpl extends CobolDivisionElementImpl implements FileSection {

	protected final FileSectionContext ctx;

	protected List<FileDescriptionEntry> fileDescriptionEntries = new ArrayList<FileDescriptionEntry>();

	public FileSectionImpl(final ProgramUnit programUnit, final FileSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addFileDescriptionEntry(final FileDescriptionEntry fileDescriptionEntry) {
		fileDescriptionEntries.add(fileDescriptionEntry);
	}

	@Override
	public List<FileDescriptionEntry> getFileDescriptionEntries() {
		return fileDescriptionEntries;
	}

}
