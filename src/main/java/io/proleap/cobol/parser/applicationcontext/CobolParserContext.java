/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.applicationcontext;

import io.proleap.cobol.parser.antlr.ASTTraverser;
import io.proleap.cobol.parser.antlr.NameResolver;
import io.proleap.cobol.parser.registry.SemanticGraphElementRegistry;
import io.proleap.cobol.parser.runner.CobolParserRunner;

public class CobolParserContext {

	protected static CobolParserContext instance;

	public static CobolParserContext getInstance() {
		if (instance == null) {
			instance = new CobolParserContext();
		}

		return instance;
	}

	protected ASTTraverser astTraverser;

	protected NameResolver nameResolver;

	protected CobolParserRunner parserRunner;

	protected SemanticGraphElementRegistry semanticGraphElementRegistry;

	private CobolParserContext() {
		super();
	}

	public ASTTraverser getAstTraverser() {
		return astTraverser;
	}

	public NameResolver getNameResolver() {
		return nameResolver;
	}

	public CobolParserRunner getParserRunner() {
		return parserRunner;
	}

	public SemanticGraphElementRegistry getSemanticGraphElementRegistry() {
		return semanticGraphElementRegistry;
	}

	public void reset() {
		instance = null;
	}

	public void setAstTraverser(final ASTTraverser astTraverser) {
		this.astTraverser = astTraverser;
	}

	public void setNameResolver(final NameResolver nameResolver) {
		this.nameResolver = nameResolver;
	}

	public void setParserRunner(final CobolParserRunner parserRunner) {
		this.parserRunner = parserRunner;
	}

	public void setSemanticGraphElementRegistry(final SemanticGraphElementRegistry semanticGraphElementRegistry) {
		this.semanticGraphElementRegistry = semanticGraphElementRegistry;
	}

}
