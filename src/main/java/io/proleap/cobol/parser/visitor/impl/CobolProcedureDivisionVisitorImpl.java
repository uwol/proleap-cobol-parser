/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.visitor.impl;

import org.antlr.v4.runtime.misc.NotNull;

import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;

/**
 * Visitor for analyzing declarations in the AST.
 */
public class CobolProcedureDivisionVisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolProcedureDivisionVisitorImpl(final CopyBook copyBook) {
		super(copyBook);
	}

	@Override
	public Boolean visitParagraph(@NotNull final Cobol85Parser.ParagraphContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitParagraphName(@NotNull final Cobol85Parser.ParagraphNameContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addParagraphName(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitProcedureDivision(@NotNull final Cobol85Parser.ProcedureDivisionContext ctx) {
		copyBook.addProcedureDivision(ctx);

		return visitChildren(ctx);
	}

}
