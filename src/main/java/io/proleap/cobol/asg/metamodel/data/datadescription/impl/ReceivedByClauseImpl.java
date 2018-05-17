/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.CobolParser.DataReceivedByClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.ReceivedByClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ReceivedByClauseImpl extends CobolDivisionElementImpl implements ReceivedByClause {

	protected DataReceivedByClauseContext ctx;

	protected ReceivedBy receivedBy;

	public ReceivedByClauseImpl(final ProgramUnit programUnit, final DataReceivedByClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ReceivedBy getReceivedBy() {
		return receivedBy;
	}

	@Override
	public void setReceivedBy(final ReceivedBy receivedBy) {
		this.receivedBy = receivedBy;
	}
}
