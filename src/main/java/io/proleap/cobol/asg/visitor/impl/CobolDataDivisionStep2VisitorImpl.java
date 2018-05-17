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
import io.proleap.cobol.asg.metamodel.data.screen.ScreenDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenSection;
import io.proleap.cobol.asg.util.ANTLRUtils;

/**
 * Visitor for analyzing declarations in the AST.
 */
public class CobolDataDivisionStep2VisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolDataDivisionStep2VisitorImpl(final Program program) {
		super(program);
	}

	protected DataDivision findDataDivision(final ParseTree ctx) {
		return ANTLRUtils.findParent(DataDivision.class, ctx, program.getASGElementRegistry());
	}

	protected void linkScreenDescriptionEntries(final List<ScreenDescriptionEntry> screenDescriptionEntries) {
		ScreenDescriptionEntry predecessor = null;

		for (final ScreenDescriptionEntry successor : screenDescriptionEntries) {
			if (predecessor != null) {
				linkScreenDescriptionEntries(predecessor, successor);
			}

			linkScreenDescriptionEntries(successor.getScreenDescriptionEntries());
			predecessor = successor;
		}
	}

	protected void linkScreenDescriptionEntries(final ScreenDescriptionEntry predecessor,
			final ScreenDescriptionEntry successor) {
		predecessor.setSuccessor(successor);
		successor.setPredecessor(predecessor);
	}

	protected void linkScreenDescriptionEntries(final ScreenSection screenSection) {
		final List<ScreenDescriptionEntry> rootScreenDescriptionEntries = screenSection
				.getRootScreenDescriptionEntries();
		linkScreenDescriptionEntries(rootScreenDescriptionEntries);
	}

	@Override
	public Boolean visitDataBaseSection(final CobolParser.DataBaseSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addDataBaseSection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitReportSection(final CobolParser.ReportSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addReportSection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitScreenSection(final CobolParser.ScreenSectionContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);
		final ScreenSection screenSection = dataDivision.addScreenSection(ctx);
		final Boolean result = visitChildren(ctx);

		linkScreenDescriptionEntries(screenSection);

		return result;
	}
}
