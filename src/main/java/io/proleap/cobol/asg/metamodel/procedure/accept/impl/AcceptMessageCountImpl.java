/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.accept.impl;

import io.proleap.cobol.Cobol85Parser.AcceptMessageCountStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptMessageCount;

public class AcceptMessageCountImpl extends CobolDivisionElementImpl implements AcceptMessageCount {

	protected final AcceptMessageCountStatementContext ctx;

	public AcceptMessageCountImpl(final ProgramUnit programUnit, final AcceptMessageCountStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
