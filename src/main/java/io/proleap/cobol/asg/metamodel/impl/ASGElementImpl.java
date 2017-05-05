/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ASGElement;

/**
 * http://en.wikipedia.org/wiki/Abstract_semantic_graph
 */
public abstract class ASGElementImpl implements ASGElement {

	protected final ParserRuleContext ctx;

	public ASGElementImpl(final ParserRuleContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public ParserRuleContext getCtx() {
		return ctx;
	}

}
