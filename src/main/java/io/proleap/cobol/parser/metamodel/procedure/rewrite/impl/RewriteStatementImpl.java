/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.rewrite.impl;

import io.proleap.cobol.Cobol85Parser.RewriteFromContext;
import io.proleap.cobol.Cobol85Parser.RewriteStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.Scope;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.InvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.NotInvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.rewrite.From;
import io.proleap.cobol.parser.metamodel.procedure.rewrite.RewriteStatement;

public class RewriteStatementImpl extends StatementImpl implements RewriteStatement {

	protected final RewriteStatementContext ctx;

	protected From from;

	protected InvalidKey invalidKey;

	protected NotInvalidKey notInvalidKey;

	protected Call recordCall;

	public RewriteStatementImpl(final ProgramUnit programUnit, final Scope scope, final RewriteStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public From addFrom(final RewriteFromContext ctx) {
		From result = (From) getASGElement(ctx);

		if (result == null) {
			result = new FromImpl(programUnit, ctx);

			final Call fromCall = createCall(ctx.identifier());
			result.setFromCall(fromCall);

			from = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public From getFrom() {
		return from;
	}

	@Override
	public InvalidKey getInvalidKey() {
		return invalidKey;
	}

	@Override
	public NotInvalidKey getNotInvalidKey() {
		return notInvalidKey;
	}

	@Override
	public Call getRecordCall() {
		return recordCall;
	}

	@Override
	public void setInvalidKey(final InvalidKey invalidKey) {
		this.invalidKey = invalidKey;
	}

	@Override
	public void setNotInvalidKey(final NotInvalidKey notInvalidKey) {
		this.notInvalidKey = notInvalidKey;
	}

	@Override
	public void setRecordCall(final Call recordCall) {
		this.recordCall = recordCall;
	}

}
