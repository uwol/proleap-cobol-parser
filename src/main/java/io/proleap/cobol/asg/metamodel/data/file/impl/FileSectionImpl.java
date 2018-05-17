/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.proleap.cobol.CobolParser.DataDescriptionEntryContext;
import io.proleap.cobol.CobolParser.FileDescriptionEntryContext;
import io.proleap.cobol.CobolParser.FileSectionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.FileSection;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class FileSectionImpl extends CobolDivisionElementImpl implements FileSection {

	protected final FileSectionContext ctx;

	protected List<FileDescriptionEntry> fileDescriptionEntries = new ArrayList<FileDescriptionEntry>();

	protected Map<String, FileDescriptionEntry> fileDescriptionEntriesSymbolTable = new HashMap<String, FileDescriptionEntry>();

	public FileSectionImpl(final ProgramUnit programUnit, final FileSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public FileDescriptionEntry addFileDescriptionEntry(final FileDescriptionEntryContext ctx) {
		FileDescriptionEntry result = (FileDescriptionEntry) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new FileDescriptionEntryImpl(name, programUnit, ctx);

			fileDescriptionEntries.add(result);
			fileDescriptionEntriesSymbolTable.put(getSymbol(name), result);

			final FileControlEntry fileControlEntry = findFileControlEntry(name);

			if (fileControlEntry != null) {
				linkFileDescriptionEntryWithFileControlEntry(result, fileControlEntry);
			}

			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			/*
			 * data description entries
			 */
			DataDescriptionEntryGroup currentDataDescriptionEntryGroup = null;

			for (final DataDescriptionEntryContext dataDescriptionEntryContext : ctx.dataDescriptionEntry()) {
				final DataDescriptionEntry dataDescriptionEntry = result
						.createDataDescriptionEntry(currentDataDescriptionEntryGroup, dataDescriptionEntryContext);

				if (dataDescriptionEntry instanceof DataDescriptionEntryGroup) {
					currentDataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;
				}
			}

			/*
			 * no clauses here, children are added later by
			 * CobolFileDescriptionEntryClauseVisitorImpl after DataDescriptionEntries have
			 * been analyzed
			 */

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<FileDescriptionEntry> getFileDescriptionEntries() {
		return fileDescriptionEntries;
	}

	@Override
	public FileDescriptionEntry getFileDescriptionEntry(final String name) {
		return fileDescriptionEntriesSymbolTable.get(getSymbol(name));
	}

	protected void linkFileDescriptionEntryWithFileControlEntry(final FileDescriptionEntry fileDescriptionEntry,
			final FileControlEntry fileControlEntry) {
		fileDescriptionEntry.setFileControlEntry(fileControlEntry);
		fileControlEntry.setFileDescriptionEntry(fileDescriptionEntry);
	}
}
