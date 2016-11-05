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

import io.proleap.cobol.parser.metamodel.ASGElement;
import io.proleap.cobol.parser.registry.ASGElementRegistry;

public class ASGElementRegistryImpl implements ASGElementRegistry {

	final protected Map<ParseTree, ASGElement> asgElements = new HashMap<ParseTree, ASGElement>();

	@Override
	public void addSemanticGraphElement(final ASGElement asgElement) {
		assert asgElement != null;

		final ParseTree ctx = asgElement.getCtx();

		assert ctx != null;
		assert asgElements.get(ctx) == null;

		asgElements.put(ctx, asgElement);
	}

	@Override
	public ASGElement getASGElement(final ParseTree ctx) {
		final ASGElement result = asgElements.get(ctx);
		return result;
	}
}
