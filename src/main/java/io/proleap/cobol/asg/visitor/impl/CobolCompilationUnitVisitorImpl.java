/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.visitor.impl;

import java.util.List;

import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.impl.CompilationUnitImpl;

/**
 * Visitor for collecting units in the AST.
 */
public class CobolCompilationUnitVisitorImpl extends AbstractCobolParserVisitorImpl {

	protected final String compilationUnitName;

	protected final List<String> lines;

	public CobolCompilationUnitVisitorImpl(final Program program, final String compilationUnitName,
			final List<String> lines) {
		super(program);

		this.compilationUnitName = compilationUnitName;
		this.lines = lines;
	}

	@Override
	public Boolean visitCompilationUnit(final Cobol85Parser.CompilationUnitContext ctx) {
		final CompilationUnit compilationUnit = new CompilationUnitImpl(compilationUnitName, program, ctx);
		compilationUnit.setLines(lines);

		return visitChildren(ctx);
	}

}
