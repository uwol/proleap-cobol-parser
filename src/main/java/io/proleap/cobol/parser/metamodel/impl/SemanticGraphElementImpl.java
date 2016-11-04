/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.SemanticGraphElement;
import io.proleap.cobol.parser.registry.SemanticGraphElementRegistry;

/**
 * http://en.wikipedia.org/wiki/Abstract_semantic_graph
 */
public abstract class SemanticGraphElementImpl implements SemanticGraphElement {

	protected final ParseTree ctx;

	public SemanticGraphElementImpl(final ParseTree ctx) {
		this.ctx = ctx;
	}

	@Override
	public Collection<SemanticGraphElement> getChildren() {
		final SemanticGraphElementRegistry semanticGraphElementRegistry = CobolParserContext.getInstance()
				.getSemanticGraphElementRegistry();
		final List<SemanticGraphElement> result = CobolParserContext.getInstance().getAstTraverser()
				.findSemanticGraphElementChildren(ctx, semanticGraphElementRegistry);
		return result;
	}

	@Override
	public ParseTree getCtx() {
		return ctx;
	}

	@Override
	public SemanticGraphElement getParent() {
		final SemanticGraphElementRegistry semanticGraphElementRegistry = CobolParserContext.getInstance()
				.getSemanticGraphElementRegistry();
		final SemanticGraphElement result = CobolParserContext.getInstance().getAstTraverser()
				.findParentSemanticGraphElement(ctx, semanticGraphElementRegistry);
		return result;
	}

}
