/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.visitor.impl;

import org.antlr.v4.runtime.misc.NotNull;

import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.parser.metamodel.CopyBook;

/**
 * Visitor for analyzing declarations in the AST.
 */
public class CobolProgramUnitVisitorImpl extends AbstractCobolParserVisitorImpl {

	public CobolProgramUnitVisitorImpl(final CopyBook copyBook) {
		super(copyBook);
	}

	@Override
	public Boolean visitProgramUnit(@NotNull final Cobol85Parser.ProgramUnitContext ctx) {
		copyBook.addProgramUnit(ctx);

		return visitChildren(ctx);
	}

}
