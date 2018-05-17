/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.rewrite.impl;

import io.proleap.cobol.CobolParser.RewriteFromContext;
import io.proleap.cobol.CobolParser.RewriteStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.rewrite.From;
import io.proleap.cobol.asg.metamodel.procedure.rewrite.RewriteStatement;

public class RewriteStatementImpl extends StatementImpl implements RewriteStatement {

	protected final RewriteStatementContext ctx;

	protected From from;

	protected InvalidKeyPhrase invalidKeyPhrase;

	protected NotInvalidKeyPhrase notInvalidKeyPhrase;

	protected Call recordCall;

	protected final StatementType statementType = StatementTypeEnum.REWRITE;

	public RewriteStatementImpl(final ProgramUnit programUnit, final Scope scope, final RewriteStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public From addFrom(final RewriteFromContext ctx) {
		From result = (From) getASGElement(ctx);

		if (result == null) {
			result = new FromImpl(programUnit, ctx);

			if (ctx.identifier() != null) {
				final Call fromCall = createCall(ctx.identifier());
				result.setFromCall(fromCall);
			}

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
	public InvalidKeyPhrase getInvalidKeyPhrase() {
		return invalidKeyPhrase;
	}

	@Override
	public NotInvalidKeyPhrase getNotInvalidKeyPhrase() {
		return notInvalidKeyPhrase;
	}

	@Override
	public Call getRecordCall() {
		return recordCall;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setInvalidKeyPhrase(final InvalidKeyPhrase invalidKeyPhrase) {
		this.invalidKeyPhrase = invalidKeyPhrase;
	}

	@Override
	public void setNotInvalidKeyPhrase(final NotInvalidKeyPhrase notInvalidKeyPhrase) {
		this.notInvalidKeyPhrase = notInvalidKeyPhrase;
	}

	@Override
	public void setRecordCall(final Call recordCall) {
		this.recordCall = recordCall;
	}

}
