/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.FileDescriptionEntryClauseContext;
import io.proleap.cobol.Cobol85Parser.FileDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.FileSectionContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.parser.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.file.FileSection;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

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

			/*
			 * file description entries
			 */
			for (final FileDescriptionEntryClauseContext fileDescriptionEntryClause : ctx
					.fileDescriptionEntryClause()) {
				if (fileDescriptionEntryClause.blockContainsClause() != null) {
					result.addBlockContainsClause(fileDescriptionEntryClause.blockContainsClause());
				}

				if (fileDescriptionEntryClause.externalClause() != null) {
					result.addExternalClause(fileDescriptionEntryClause.externalClause());
				}

				if (fileDescriptionEntryClause.globalClause() != null) {
					result.addGlobalClause(fileDescriptionEntryClause.globalClause());
				}

				if (fileDescriptionEntryClause.codeSetClause() != null) {
					result.addCodeSetClause(fileDescriptionEntryClause.codeSetClause());
				}

				if (fileDescriptionEntryClause.recordContainsClause() != null) {
					result.addRecordContainsClause(fileDescriptionEntryClause.recordContainsClause());
				}

				if (fileDescriptionEntryClause.labelRecordsClause() != null) {
					result.addLabelRecordsClause(fileDescriptionEntryClause.labelRecordsClause());
				}

				if (fileDescriptionEntryClause.valueOfClause() != null) {
					result.addValueOfClause(fileDescriptionEntryClause.valueOfClause());
				}

				if (fileDescriptionEntryClause.linageClause() != null) {
					result.addLinageClause(fileDescriptionEntryClause.linageClause());
				}

				if (fileDescriptionEntryClause.dataRecordsClause() != null) {
					result.addDataRecordsClause(fileDescriptionEntryClause.dataRecordsClause());
				}

				if (fileDescriptionEntryClause.reportClause() != null) {
					result.addReportClause(fileDescriptionEntryClause.reportClause());
				}
			}

			/*
			 * data description entries
			 */
			DataDescriptionEntryGroup lastDataDescriptionEntryGroup = null;

			for (final DataDescriptionEntryContext dataDescriptionEntryContext : ctx.dataDescriptionEntry()) {
				final DataDescriptionEntry dataDescriptionEntry = result
						.createDataDescriptionEntry(lastDataDescriptionEntryGroup, dataDescriptionEntryContext);

				if (dataDescriptionEntry instanceof DataDescriptionEntryGroup) {
					lastDataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;
				}
			}

			fileDescriptionEntries.add(result);
			fileDescriptionEntriesSymbolTable.put(name, result);

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
		return fileDescriptionEntriesSymbolTable.get(name);
	}
}
