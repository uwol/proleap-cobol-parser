/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.visitor.impl;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.CobolParser;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.FileSection;
import io.proleap.cobol.asg.metamodel.data.linkage.LinkageSection;
import io.proleap.cobol.asg.metamodel.data.localstorage.LocalStorageSection;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.util.ANTLRUtils;

/**
 * Visitor for analyzing declarations in the AST.
 */
public class CobolDataDivisionStep1VisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolDataDivisionStep1VisitorImpl(final Program program) {
		super(program);
	}

	protected DataDivision findDataDivision(final ParseTree ctx) {
		return ANTLRUtils.findParent(DataDivision.class, ctx, program.getASGElementRegistry());
	}

	protected void linkDataDescriptionEntries(final DataDescriptionEntry predecessor,
			final DataDescriptionEntry successor) {
		predecessor.setDataDescriptionEntrySuccessor(successor);
		successor.setDataDescriptionEntryPredecessor(predecessor);
	}

	protected void linkDataDescriptionEntries(final DataDescriptionEntryContainer container) {
		final List<DataDescriptionEntry> rootDataDescriptionEntries = container.getRootDataDescriptionEntries();
		linkDataDescriptionEntries(rootDataDescriptionEntries);
	}

	protected void linkDataDescriptionEntries(final List<DataDescriptionEntry> dataDescriptionEntries) {
		DataDescriptionEntry predecessor = null;

		for (final DataDescriptionEntry successor : dataDescriptionEntries) {
			if (predecessor != null) {
				linkDataDescriptionEntries(predecessor, successor);
			}

			if (DataDescriptionEntryType.GROUP.equals(successor.getDataDescriptionEntryType())) {
				final DataDescriptionEntryGroup successorGroup = (DataDescriptionEntryGroup) successor;
				linkDataDescriptionEntries(successorGroup.getDataDescriptionEntries());
			}

			predecessor = successor;
		}
	}

	@Override
	public Boolean visitCommunicationSection(final CobolParser.CommunicationSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);
		final CommunicationSection communicationSection = dataDivision.addCommunicationSection(ctx);
		final Boolean result = visitChildren(ctx);

		linkDataDescriptionEntries(communicationSection);

		return result;
	}

	@Override
	public Boolean visitFileSection(final CobolParser.FileSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);
		final FileSection fileSection = dataDivision.addFileSection(ctx);
		final Boolean result = visitChildren(ctx);

		for (final FileDescriptionEntry fileDescriptionEntry : fileSection.getFileDescriptionEntries()) {
			linkDataDescriptionEntries(fileDescriptionEntry);
		}

		return result;
	}

	@Override
	public Boolean visitLinkageSection(final CobolParser.LinkageSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);
		final LinkageSection linkageSection = dataDivision.addLinkageSection(ctx);
		final Boolean result = visitChildren(ctx);

		linkDataDescriptionEntries(linkageSection);

		return result;
	}

	@Override
	public Boolean visitLocalStorageSection(final CobolParser.LocalStorageSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);
		final LocalStorageSection localStorageSection = dataDivision.addLocalStorageSection(ctx);
		final Boolean result = visitChildren(ctx);

		linkDataDescriptionEntries(localStorageSection);

		return result;
	}

	@Override
	public Boolean visitProgramLibrarySection(final CobolParser.ProgramLibrarySectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addProgramLibrarySection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitWorkingStorageSection(final CobolParser.WorkingStorageSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);
		final WorkingStorageSection workingStorageSection = dataDivision.addWorkingStorageSection(ctx);
		final Boolean result = visitChildren(ctx);

		linkDataDescriptionEntries(workingStorageSection);

		return result;
	}
}
