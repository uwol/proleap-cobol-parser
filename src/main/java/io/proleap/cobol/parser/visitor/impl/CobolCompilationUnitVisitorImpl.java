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
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.impl.CompilationUnitImpl;

/**
 * Visitor for collecting units in the AST.
 */
public class CobolCompilationUnitVisitorImpl extends AbstractCobolParserVisitorImpl {

	protected final String compilationUnitName;

	protected final Program program;

	public CobolCompilationUnitVisitorImpl(final Program program, final String compilationUnitName) {
		this.program = program;
		this.compilationUnitName = compilationUnitName;
	}

	@Override
	public Boolean visitCompilationUnit(@NotNull final Cobol85Parser.CompilationUnitContext ctx) {
		new CompilationUnitImpl(compilationUnitName, program, ctx);

		return visitChildren(ctx);
	}

}
