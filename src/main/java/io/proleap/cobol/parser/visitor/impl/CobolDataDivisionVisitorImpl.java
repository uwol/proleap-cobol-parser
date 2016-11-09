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

/**
 * Visitor for analyzing declarations in the AST.
 */
public class CobolDataDivisionVisitorImpl extends AbstractCobolParserVisitorImpl {

	protected DataDivision findDataDivision(final ParseTree ctx) {
		final ASGElementRegistry registry = CobolParserContext.getInstance().getASGElementRegistry();
		return CobolParserContext.getInstance().getAstTraverser().findParent(DataDivision.class, ctx, registry);
	}

	@Override
	public Boolean visitDataDescriptionEntryFormat1(
			@NotNull final Cobol85Parser.DataDescriptionEntryFormat1Context ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addDataDescriptionEntry(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDataDescriptionEntryFormat2(
			@NotNull final Cobol85Parser.DataDescriptionEntryFormat2Context ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addDataDescriptionEntry(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDataDescriptionEntryFormat3(
			@NotNull final Cobol85Parser.DataDescriptionEntryFormat3Context ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addDataDescriptionEntry(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDataDivisionBody(@NotNull final Cobol85Parser.DataDivisionBodyContext ctx) {
		final DataDivision dataDivision = findDataDivision(ctx);

		dataDivision.addDataDivisionBody(ctx);

		return visitChildren(ctx);
	}

}
