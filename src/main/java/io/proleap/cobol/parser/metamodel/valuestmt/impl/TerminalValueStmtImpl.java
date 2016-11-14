/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.valuestmt.impl;

import org.antlr.v4.runtime.tree.TerminalNode;

import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.valuestmt.TerminalValueStmt;

public class TerminalValueStmtImpl extends ValueStmtImpl implements TerminalValueStmt {

	protected TerminalNode terminal;

	public TerminalValueStmtImpl(final ProgramUnit programUnit, final TerminalNode ctx) {
		super(programUnit, ctx);

		terminal = ctx;
	}

	@Override
	public TerminalNode getTerminal() {
		return terminal;
	}

	@Override
	public String getValue() {
		final String result = terminal != null ? terminal.getText() : null;
		return result;
	}

	@Override
	public String toString() {
		return terminal != null ? terminal.toString() : null;
	}
}
