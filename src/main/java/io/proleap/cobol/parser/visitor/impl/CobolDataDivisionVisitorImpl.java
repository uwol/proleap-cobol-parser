/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.visitor.impl;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.registry.ASGElementRegistry;
import io.proleap.cobol.parser.util.ANTLRUtils;

/**
 * Visitor for analyzing declarations in the AST.
 */
public class CobolDataDivisionVisitorImpl extends AbstractCobolParserVisitorImpl {

	protected DataDivision findDataDivision(final ParseTree ctx) {
		final ASGElementRegistry registry = CobolParserContext.getInstance().getASGElementRegistry();
		return ANTLRUtils.findParent(DataDivision.class, ctx, registry);
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
