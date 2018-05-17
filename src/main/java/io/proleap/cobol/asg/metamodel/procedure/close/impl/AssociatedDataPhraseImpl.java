/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.close.impl;

import io.proleap.cobol.CobolParser.ClosePortFileIOUsingAssociatedDataContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.close.AssociatedDataPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class AssociatedDataPhraseImpl extends CobolDivisionElementImpl implements AssociatedDataPhrase {

	protected ClosePortFileIOUsingAssociatedDataContext ctx;

	protected ValueStmt dataValueStmt;

	public AssociatedDataPhraseImpl(final ProgramUnit programUnit, final ClosePortFileIOUsingAssociatedDataContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getDataValueStmt() {
		return dataValueStmt;
	}

	@Override
	public void setDataValueStmt(final ValueStmt dataValueStmt) {
		this.dataValueStmt = dataValueStmt;
	}

}
