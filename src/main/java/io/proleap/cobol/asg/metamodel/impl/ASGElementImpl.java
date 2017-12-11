/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ASGElement;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.registry.ASGElementRegistry;
import io.proleap.cobol.asg.util.ANTLRUtils;

/**
 * http://en.wikipedia.org/wiki/Abstract_semantic_graph
 */
public abstract class ASGElementImpl implements ASGElement {

	protected final ParserRuleContext ctx;

	protected final Program program;

	public ASGElementImpl(final Program program, final ParserRuleContext ctx) {
		this.program = program;
		this.ctx = ctx;
	}

	@Override
	public List<ASGElement> getChildren() {
		final ASGElementRegistry asgElementRegistry = program.getASGElementRegistry();
		final List<ASGElement> result = ANTLRUtils.findASGElementChildren(ctx, asgElementRegistry);
		return result;
	}

	@Override
	public ParserRuleContext getCtx() {
		return ctx;
	}

	@Override
	public ASGElement getParent() {
		final ASGElementRegistry asgElementRegistry = program.getASGElementRegistry();
		final ASGElement result = ANTLRUtils.findParent(ASGElement.class, ctx, asgElementRegistry);
		return result;
	}

	@Override
	public Program getProgram() {
		return program;
	}
}
