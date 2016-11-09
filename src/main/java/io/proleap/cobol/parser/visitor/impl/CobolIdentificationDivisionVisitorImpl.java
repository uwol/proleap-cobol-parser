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
public class CobolIdentificationDivisionVisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolIdentificationDivisionVisitorImpl(final CopyBook copyBook) {
		super(copyBook);
	}

	@Override
	public Boolean visitAuthorParagraph(@NotNull final Cobol85Parser.AuthorParagraphContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addAuthorParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDateCompiledParagraph(@NotNull final Cobol85Parser.DateCompiledParagraphContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addDateCompiledParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDateWrittenParagraph(@NotNull final Cobol85Parser.DateWrittenParagraphContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addDateWrittenParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitIdentificationDivision(@NotNull final Cobol85Parser.IdentificationDivisionContext ctx) {
		copyBook.addIdentificationDivision(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitInstallationParagraph(@NotNull final Cobol85Parser.InstallationParagraphContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addInstallationParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitProgramIdParagraph(@NotNull final Cobol85Parser.ProgramIdParagraphContext ctx) {
		copyBook.addProgramIdParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRemarksParagraph(@NotNull final Cobol85Parser.RemarksParagraphContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addRemarksParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSecurityParagraph(@NotNull final Cobol85Parser.SecurityParagraphContext ctx) {
		final CobolScope scope = findScope(ctx);

		scope.addSecurityParagraph(ctx);

		return visitChildren(ctx);
	}

}
