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

import io.proleap.cobol.parser.metamodel.SemanticGraphElement;
import io.proleap.cobol.parser.registry.SemanticGraphElementRegistry;

public interface ASTTraverser {

	<T extends SemanticGraphElement> Collection<T> findAncestors(Class<? extends SemanticGraphElement> type,
			ParseTree from, SemanticGraphElementRegistry semanticGraphElementRegistry);

	<T extends SemanticGraphElement> List<T> findChildren(Class<? extends SemanticGraphElement> type, ParseTree ctx,
			SemanticGraphElementRegistry semanticGraphElementRegistry);

	List<ParseTree> findChildren(ParseTree ctx);

	<T extends SemanticGraphElement> T findParent(Class<? extends SemanticGraphElement> type, ParseTree from,
			SemanticGraphElementRegistry semanticGraphElementRegistry);

	/**
	 * Identifies the parent semantic graph element, comparable to the AST
	 * parent.
	 */
	SemanticGraphElement findParentSemanticGraphElement(ParseTree ctx,
			SemanticGraphElementRegistry semanticGraphElementRegistry);

	List<SemanticGraphElement> findSemanticGraphElementChildren(ParseTree from,
			SemanticGraphElementRegistry semanticGraphElementRegistry);
}
