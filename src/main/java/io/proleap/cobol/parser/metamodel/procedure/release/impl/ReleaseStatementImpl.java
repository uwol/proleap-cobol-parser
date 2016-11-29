/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.release.impl;

import io.proleap.cobol.Cobol85Parser.ReleaseStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.Scope;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.release.ReleaseStatement;

public class ReleaseStatementImpl extends StatementImpl implements ReleaseStatement {

	protected Call contentCall;

	protected final ReleaseStatementContext ctx;

	protected Call recordCall;

	public ReleaseStatementImpl(final ProgramUnit programUnit, final Scope scope, final ReleaseStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getContentCall() {
		return contentCall;
	}

	@Override
	public Call getRecordCall() {
		return recordCall;
	}

	@Override
	public void setContentCall(final Call contentCall) {
		this.contentCall = contentCall;
	}

	@Override
	public void setRecordCall(final Call recordCall) {
		this.recordCall = recordCall;
	}

}
