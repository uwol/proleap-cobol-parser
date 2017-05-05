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
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.util.ANTLRUtils;

/**
 * Visitor for analyzing declarations in the AST.
 */
public class CobolProcedureDivisionVisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolProcedureDivisionVisitorImpl(final Program program) {
		super(program);
	}

	protected ProcedureDivision findProcedureDivision(final ParseTree ctx) {
		return ANTLRUtils.findParent(ProcedureDivision.class, ctx, program.getASGElementRegistry());
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
