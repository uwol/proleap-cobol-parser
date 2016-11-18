/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataThreadLocalClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.ThreadLocalClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class ThreadLocalClauseImpl extends CobolDivisionElementImpl implements ThreadLocalClause {

	protected DataThreadLocalClauseContext ctx;

	protected Boolean threadLocal;

	public ThreadLocalClauseImpl(final ProgramUnit programUnit, final DataThreadLocalClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Boolean isThreadLocal() {
		return threadLocal;
	}

	@Override
	public void setThreadLocal(final Boolean threadLocal) {
		this.threadLocal = threadLocal;
	}

}
