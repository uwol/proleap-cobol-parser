/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.CompilationUnitElement;
import io.proleap.cobol.asg.resolver.impl.NameResolverImpl;

public class CompilationUnitElementImpl extends ASGElementImpl implements CompilationUnitElement {

	protected final CompilationUnit compilationUnit;

	public CompilationUnitElementImpl(final CompilationUnit compilationUnit, final ParserRuleContext ctx) {
		super(compilationUnit.getProgram(), ctx);

		this.compilationUnit = compilationUnit;
	}

	protected String determineName(final ParserRuleContext ctx) {
		return new NameResolverImpl().determineName(ctx);
	}

	@Override
	public CompilationUnit getCompilationUnit() {
		return compilationUnit;
	}
}
