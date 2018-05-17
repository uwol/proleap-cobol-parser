/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.CobolParser.DataThreadLocalClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.ThreadLocalClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ThreadLocalClauseImpl extends CobolDivisionElementImpl implements ThreadLocalClause {

	protected DataThreadLocalClauseContext ctx;

	protected boolean threadLocal;

	public ThreadLocalClauseImpl(final ProgramUnit programUnit, final DataThreadLocalClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public boolean isThreadLocal() {
		return threadLocal;
	}

	@Override
	public void setThreadLocal(final boolean threadLocal) {
		this.threadLocal = threadLocal;
	}

}
