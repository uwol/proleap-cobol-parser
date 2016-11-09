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
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.registry.ASGElementRegistry;

/**
 * Visitor for analyzing declarations in the AST.
 */
public class CobolProcedureDivisionVisitorImpl extends AbstractCobolParserVisitorImpl {

	protected ProcedureDivision findProcedureDivision(final ParseTree ctx) {
		final ASGElementRegistry registry = CobolParserContext.getInstance().getASGElementRegistry();
		return CobolParserContext.getInstance().getAstTraverser().findParent(ProcedureDivision.class, ctx, registry);
	}

	@Override
	public Boolean visitParagraph(@NotNull final Cobol85Parser.ParagraphContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitParagraphName(@NotNull final Cobol85Parser.ParagraphNameContext ctx) {
		final ProcedureDivision procedureDivision = findProcedureDivision(ctx);

		procedureDivision.addParagraphName(ctx);

		return visitChildren(ctx);
	}

}
