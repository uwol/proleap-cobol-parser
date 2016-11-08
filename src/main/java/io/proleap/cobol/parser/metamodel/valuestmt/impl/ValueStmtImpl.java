/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.valuestmt.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.impl.CobolScopedElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public abstract class ValueStmtImpl extends CobolScopedElementImpl implements ValueStmt {

	protected final ParseTree ctx;

	protected final List<ValueStmt> subValueStmts = new ArrayList<ValueStmt>();

	public ValueStmtImpl(final CopyBook copyBook, final CobolScope superScope, final ParseTree ctx) {
		super(copyBook, superScope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addSubValueStmt(final ValueStmt valueStmt) {
		subValueStmts.add(valueStmt);
	}

	@Override
	public List<ValueStmt> getSubValueStmts() {
		return subValueStmts;
	}

}
