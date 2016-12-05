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
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.asg.registry.ASGElementRegistry;
import io.proleap.cobol.asg.util.ANTLRUtils;

/**
 * Visitor for analyzing declarations in the AST.
 */
public class CobolIdentificationDivisionVisitorImpl extends AbstractCobolParserVisitorImpl {

	protected IdentificationDivision findIdentificationDivision(final ParseTree ctx) {
		final ASGElementRegistry registry = CobolParserContext.getInstance().getASGElementRegistry();
		return ANTLRUtils.findParent(IdentificationDivision.class, ctx, registry);
	}

	@Override
	public Boolean visitAuthorParagraph(@NotNull final Cobol85Parser.AuthorParagraphContext ctx) {
		final IdentificationDivision identificationDivision = findIdentificationDivision(ctx);

		identificationDivision.addAuthorParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDateCompiledParagraph(@NotNull final Cobol85Parser.DateCompiledParagraphContext ctx) {
		final IdentificationDivision identificationDivision = findIdentificationDivision(ctx);

		identificationDivision.addDateCompiledParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDateWrittenParagraph(@NotNull final Cobol85Parser.DateWrittenParagraphContext ctx) {
		final IdentificationDivision identificationDivision = findIdentificationDivision(ctx);

		identificationDivision.addDateWrittenParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitInstallationParagraph(@NotNull final Cobol85Parser.InstallationParagraphContext ctx) {
		final IdentificationDivision identificationDivision = findIdentificationDivision(ctx);

		identificationDivision.addInstallationParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitProgramIdParagraph(@NotNull final Cobol85Parser.ProgramIdParagraphContext ctx) {
		final IdentificationDivision identificationDivision = findIdentificationDivision(ctx);

		identificationDivision.addProgramIdParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRemarksParagraph(@NotNull final Cobol85Parser.RemarksParagraphContext ctx) {
		final IdentificationDivision identificationDivision = findIdentificationDivision(ctx);

		identificationDivision.addRemarksParagraph(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSecurityParagraph(@NotNull final Cobol85Parser.SecurityParagraphContext ctx) {
		final IdentificationDivision identificationDivision = findIdentificationDivision(ctx);

		identificationDivision.addSecurityParagraph(ctx);

		return visitChildren(ctx);
	}

}
