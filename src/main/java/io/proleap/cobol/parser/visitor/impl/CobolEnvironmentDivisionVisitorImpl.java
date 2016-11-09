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
public class CobolEnvironmentDivisionVisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolEnvironmentDivisionVisitorImpl(final CopyBook copyBook) {
		super(copyBook);
	}

	@Override
	public Boolean visitConfigurationSection(@NotNull final Cobol85Parser.ConfigurationSectionContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addConfigurationSection(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEnvironmentDivision(@NotNull final Cobol85Parser.EnvironmentDivisionContext ctx) {
		copyBook.addEnvironmentDivision(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitObjectComputerParagraph(@NotNull final Cobol85Parser.ObjectComputerParagraphContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addObjectComputerParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSourceComputerParagraph(@NotNull final Cobol85Parser.SourceComputerParagraphContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addSourceComputerParagraph(ctx);

		return visitChildren(ctx);
	}

}
