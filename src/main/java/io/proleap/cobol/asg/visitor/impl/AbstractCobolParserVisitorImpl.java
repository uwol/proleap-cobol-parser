/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.visitor.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.CobolBaseVisitor;
import io.proleap.cobol.asg.metamodel.ASGElement;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.util.ANTLRUtils;
import io.proleap.cobol.asg.visitor.ParserVisitor;

public abstract class AbstractCobolParserVisitorImpl extends CobolBaseVisitor<Boolean> implements ParserVisitor {

	protected Program program;

	public AbstractCobolParserVisitorImpl(final Program program) {
		this.program = program;
	}

	protected CompilationUnit findCompilationUnit(final ParseTree ctx) {
		return ANTLRUtils.findParent(CompilationUnit.class, ctx, program.getASGElementRegistry());
	}

	protected ProgramUnit findProgramUnit(final ParseTree ctx) {
		return ANTLRUtils.findParent(ProgramUnit.class, ctx, program.getASGElementRegistry());
	}

	protected Scope findScope(final ParseTree ctx) {
		return ANTLRUtils.findParent(Scope.class, ctx, program.getASGElementRegistry());
	}

	protected ASGElement getASGElement(final ParseTree ctx) {
		final ASGElement result = program.getASGElementRegistry().getASGElement(ctx);
		return result;
	}
}
