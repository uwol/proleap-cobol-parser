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
public class CobolDeclarationVisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolDeclarationVisitorImpl(final CopyBook copyBook) {
		super(copyBook);
	}

	@Override
	public Boolean visitDataDescriptionEntryFormat1(
			@NotNull final Cobol85Parser.DataDescriptionEntryFormat1Context ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addDataDescriptionEntry(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitIdentificationDivision(@NotNull final Cobol85Parser.IdentificationDivisionContext ctx) {
		copyBook.addIdentificationDivision(ctx);

		return visitChildren(ctx);
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

	@Override
	public Boolean visitProgramIdParagraph(@NotNull final Cobol85Parser.ProgramIdParagraphContext ctx) {
		copyBook.addProgramIdParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitProgramUnit(@NotNull final Cobol85Parser.ProgramUnitContext ctx) {
		copyBook.addProgramUnit(ctx);

		return visitChildren(ctx);
	}

}
