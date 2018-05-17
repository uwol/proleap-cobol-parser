/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.impl;

import io.proleap.cobol.CobolParser.OnExceptionClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.ScopeImpl;
import io.proleap.cobol.asg.metamodel.procedure.OnExceptionClause;

public class OnExceptionClauseImpl extends ScopeImpl implements OnExceptionClause {

	protected final OnExceptionClauseContext ctx;

	public OnExceptionClauseImpl(final ProgramUnit programUnit, final OnExceptionClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}
}
