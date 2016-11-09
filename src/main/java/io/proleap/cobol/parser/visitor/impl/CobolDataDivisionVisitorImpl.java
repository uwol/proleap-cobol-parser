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
public class CobolDataDivisionVisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolDataDivisionVisitorImpl(final CopyBook copyBook) {
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
	public Boolean visitDataDescriptionEntryFormat2(
			@NotNull final Cobol85Parser.DataDescriptionEntryFormat2Context ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addDataDescriptionEntry(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDataDescriptionEntryFormat3(
			@NotNull final Cobol85Parser.DataDescriptionEntryFormat3Context ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addDataDescriptionEntry(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDataDivision(@NotNull final Cobol85Parser.DataDivisionContext ctx) {
		copyBook.addDataDivision(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDataDivisionBody(@NotNull final Cobol85Parser.DataDivisionBodyContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addDataDivisionBody(ctx);

		return visitChildren(ctx);
	}

}
