/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.proleap.cobol.CobolParser.FileControlEntryContext;
import io.proleap.cobol.CobolParser.FileControlParagraphContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class FileControlParagraphImpl extends CobolDivisionElementImpl implements FileControlParagraph {

	protected final FileControlParagraphContext ctx;

	protected final List<FileControlEntry> fileControlEntries = new ArrayList<FileControlEntry>();

	protected Map<String, FileControlEntry> fileControlEntriesSymbolTable = new HashMap<String, FileControlEntry>();

	public FileControlParagraphImpl(final ProgramUnit programUnit, final FileControlParagraphContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public FileControlEntry addFileControlEntry(final FileControlEntryContext ctx) {
		FileControlEntry result = (FileControlEntry) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new FileControlEntryImpl(name, programUnit, ctx);

			/*
			 * no clauses here, children are added later by
			 * CobolFileControlClauseVisitorImpl after DataDescriptionEntries have been
			 * analyzed
			 */

			registerASGElement(result);

			fileControlEntries.add(result);
			fileControlEntriesSymbolTable.put(getSymbol(name), result);
		}

		return result;
	}

	@Override
	public List<FileControlEntry> getFileControlEntries() {
		return fileControlEntries;
	}

	@Override
	public FileControlEntry getFileControlEntry(final String name) {
		return fileControlEntriesSymbolTable.get(getSymbol(name));
	}
}
