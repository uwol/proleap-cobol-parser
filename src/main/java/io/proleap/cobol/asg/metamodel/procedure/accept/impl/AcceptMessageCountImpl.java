/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.accept.impl;

import io.proleap.cobol.CobolParser.AcceptMessageCountStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptMessageCountStatement;

public class AcceptMessageCountImpl extends CobolDivisionElementImpl implements AcceptMessageCountStatement {

	protected final AcceptMessageCountStatementContext ctx;

	public AcceptMessageCountImpl(final ProgramUnit programUnit, final AcceptMessageCountStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
