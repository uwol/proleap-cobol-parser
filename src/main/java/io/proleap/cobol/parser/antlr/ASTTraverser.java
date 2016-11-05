/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.antlr;

import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.ASGElement;
import io.proleap.cobol.parser.registry.ASGElementRegistry;

public interface ASTTraverser {

	<T extends ASGElement> Collection<T> findAncestors(Class<? extends ASGElement> type,
			ParseTree from, ASGElementRegistry asgElementRegistry);

	<T extends ASGElement> List<T> findChildren(Class<? extends ASGElement> type, ParseTree ctx,
			ASGElementRegistry asgElementRegistry);

	List<ParseTree> findChildren(ParseTree ctx);

	<T extends ASGElement> T findParent(Class<? extends ASGElement> type, ParseTree from,
			ASGElementRegistry asgElementRegistry);

	/**
	 * Identifies the parent semantic graph element, comparable to the AST
	 * parent.
	 */
	ASGElement findParentSemanticGraphElement(ParseTree ctx,
			ASGElementRegistry asgElementRegistry);

	List<ASGElement> findSemanticGraphElementChildren(ParseTree from,
			ASGElementRegistry asgElementRegistry);
}
