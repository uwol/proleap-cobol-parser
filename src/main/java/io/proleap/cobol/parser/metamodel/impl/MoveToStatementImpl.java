/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.MoveToStatement;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class MoveToStatementImpl extends CobolScopedElementImpl implements MoveToStatement {

	protected final MoveToStatementContext ctx;

	protected List<Call> receivingAreaCalls = new ArrayList<Call>();

	protected ValueStmt sendingAreaValueStmt;

	public MoveToStatementImpl(final CopyBook copyBook, final CobolScope superScope, final MoveToStatementContext ctx) {
		super(copyBook, superScope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addReceivingAreaCall(final Call receivingAreaCall) {
		receivingAreaCalls.add(receivingAreaCall);
	}

	@Override
	public void setSendingAreaValueStmt(final ValueStmt sendingAreaValueStmt) {
		this.sendingAreaValueStmt = sendingAreaValueStmt;
	}

}
