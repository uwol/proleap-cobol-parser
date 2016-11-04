/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.antlr.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.antlr.ASTTraverser;
import io.proleap.cobol.parser.metamodel.SemanticGraphElement;
import io.proleap.cobol.parser.registry.SemanticGraphElementRegistry;

public class ASTTraverserImpl implements ASTTraverser {

	@Override
	public <T extends SemanticGraphElement> Collection<T> findAncestors(
			final Class<? extends SemanticGraphElement> type, final ParseTree from,
			final SemanticGraphElementRegistry semanticGraphElementRegistry) {
		ParseTree currentCtx = from;

		final Collection<T> result = new ArrayList<T>();

		while (currentCtx != null) {
			final T parent = findParent(type, currentCtx, semanticGraphElementRegistry);

			if (parent != null) {
				currentCtx = parent.getCtx();
				result.add(parent);
			} else {
				currentCtx = null;
			}
		}

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends SemanticGraphElement> List<T> findChildren(final Class<? extends SemanticGraphElement> type,
			final ParseTree ctx, final SemanticGraphElementRegistry semanticGraphElementRegistry) {
		final List<ParseTree> children = findChildren(ctx);
		final List<T> result = new ArrayList<T>();

		for (final ParseTree currentChild : children) {
			final SemanticGraphElement semanticGraphElement = semanticGraphElementRegistry
					.getSemanticGraphElement(currentChild);

			if (semanticGraphElement != null && type.isAssignableFrom(semanticGraphElement.getClass())) {
				result.add((T) semanticGraphElement);
			}
		}

		return result;
	}

	@Override
	public List<ParseTree> findChildren(final ParseTree ctx) {
		final List<ParseTree> result = new ArrayList<ParseTree>();
		final int n = ctx.getChildCount();

		for (int i = 0; i < n; i++) {
			final ParseTree currentChild = ctx.getChild(i);
			result.add(currentChild);
		}

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends SemanticGraphElement> T findParent(final Class<? extends SemanticGraphElement> type,
			final ParseTree from, final SemanticGraphElementRegistry semanticGraphElementRegistry) {
		T result = null;

		ParseTree currentCtx = from;

		while (result == null && currentCtx != null) {
			currentCtx = currentCtx.getParent();

			final SemanticGraphElement semanticGraphElement = semanticGraphElementRegistry
					.getSemanticGraphElement(currentCtx);

			if (semanticGraphElement != null && type.isAssignableFrom(semanticGraphElement.getClass())) {
				result = (T) semanticGraphElement;
			}
		}

		return result;
	}

	@Override
	public SemanticGraphElement findParentSemanticGraphElement(final ParseTree from,
			final SemanticGraphElementRegistry semanticGraphElementRegistry) {
		return findParent(SemanticGraphElement.class, from, semanticGraphElementRegistry);
	}

	@Override
	public List<SemanticGraphElement> findSemanticGraphElementChildren(final ParseTree from,
			final SemanticGraphElementRegistry semanticGraphElementRegistry) {
		return findChildren(SemanticGraphElement.class, from, semanticGraphElementRegistry);
	}
}
