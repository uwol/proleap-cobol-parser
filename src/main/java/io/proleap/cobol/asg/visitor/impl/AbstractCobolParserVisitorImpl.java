/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.visitor.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.Cobol85BaseVisitor;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.ASGElement;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.registry.ASGElementRegistry;
import io.proleap.cobol.asg.util.ANTLRUtils;
import io.proleap.cobol.asg.visitor.ParserVisitor;

public abstract class AbstractCobolParserVisitorImpl extends Cobol85BaseVisitor<Boolean> implements ParserVisitor {

	protected CompilationUnit findCompilationUnit(final ParseTree ctx) {
		final ASGElementRegistry registry = CobolParserContext.getInstance().getASGElementRegistry();
		return ANTLRUtils.findParent(CompilationUnit.class, ctx, registry);
	}

	protected ProgramUnit findProgramUnit(final ParseTree ctx) {
		final ASGElementRegistry registry = CobolParserContext.getInstance().getASGElementRegistry();
		return ANTLRUtils.findParent(ProgramUnit.class, ctx, registry);
	}

	protected Scope findScope(final ParseTree ctx) {
		final ASGElementRegistry registry = CobolParserContext.getInstance().getASGElementRegistry();
		return ANTLRUtils.findParent(Scope.class, ctx, registry);
	}

	protected ASGElement getASGElement(final ParseTree ctx) {
		final ASGElement result = CobolParserContext.getInstance().getASGElementRegistry().getASGElement(ctx);
		return result;
	}
}
