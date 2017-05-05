/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.visitor.impl;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.util.ANTLRUtils;

/**
 * Visitor for analyzing declarations in the AST.
 */
public class CobolDataDivisionVisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolDataDivisionVisitorImpl(final Program program) {
		super(program);
	}

	protected DataDivision findDataDivision(final ParseTree ctx) {
		return ANTLRUtils.findParent(DataDivision.class, ctx, program.getASGElementRegistry());
	}

	@Override
	public Boolean visitCommunicationSection(@NotNull final Cobol85Parser.CommunicationSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addCommunicationSection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDataBaseSection(@NotNull final Cobol85Parser.DataBaseSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addDataBaseSection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitFileSection(@NotNull final Cobol85Parser.FileSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addFileSection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLinkageSection(@NotNull final Cobol85Parser.LinkageSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addLinkageSection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLocalStorageSection(@NotNull final Cobol85Parser.LocalStorageSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addLocalStorageSection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitProgramLibrarySection(@NotNull final Cobol85Parser.ProgramLibrarySectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addProgramLibrarySection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReportSection(@NotNull final Cobol85Parser.ReportSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addReportSection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitScreenSection(@NotNull final Cobol85Parser.ScreenSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addScreenSection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitWorkingStorageSection(@NotNull final Cobol85Parser.WorkingStorageSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addWorkingStorageSection(ctx);

		return visitChildren(ctx);
	}

}
