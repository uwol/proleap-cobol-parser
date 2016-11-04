/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.registry.impl;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.SemanticGraphElement;
import io.proleap.cobol.parser.registry.SemanticGraphElementRegistry;

public class SemanticGraphElementRegistryImpl implements SemanticGraphElementRegistry {

	final protected Map<ParseTree, SemanticGraphElement> semanticGraphElements = new HashMap<ParseTree, SemanticGraphElement>();

	@Override
	public void addSemanticGraphElement(final SemanticGraphElement semanticGraphElement) {
		assert semanticGraphElement != null;

		final ParseTree ctx = semanticGraphElement.getCtx();

		assert ctx != null;
		assert semanticGraphElements.get(ctx) == null;

		semanticGraphElements.put(ctx, semanticGraphElement);
	}

	@Override
	public SemanticGraphElement getSemanticGraphElement(final ParseTree ctx) {
		final SemanticGraphElement result = semanticGraphElements.get(ctx);
		return result;
	}
}
