/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.visitor.impl;

import io.proleap.cobol.parser.metamodel.Program;

/**
 * Visitor for collecting type declarations in the AST.
 */
public class CobolProgramUnitVisitorImpl extends AbstractCobolParserVisitorImpl {

	protected final String moduleName;

	protected final Program program;

	public CobolProgramUnitVisitorImpl(final Program program, final String moduleName) {
		super(null);

		this.program = program;
		this.moduleName = moduleName;
	}

}
