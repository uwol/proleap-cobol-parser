/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.returnstmt.impl;

import io.proleap.cobol.CobolParser.ReturnIntoContext;
import io.proleap.cobol.CobolParser.ReturnStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.AtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotAtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.returnstmt.Into;
import io.proleap.cobol.asg.metamodel.procedure.returnstmt.ReturnStatement;

public class ReturnStatementImpl extends StatementImpl implements ReturnStatement {

	protected AtEndPhrase atEndPhrase;

	protected final ReturnStatementContext ctx;

	protected Call fileCall;

	protected Into into;

	protected NotAtEndPhrase notAtEndPhrase;

	protected final StatementType statementType = StatementTypeEnum.RETURN;

	public ReturnStatementImpl(final ProgramUnit programUnit, final Scope scope, final ReturnStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

	@Override
	public Into addInto(final ReturnIntoContext ctx) {
		Into result = (Into) getASGElement(ctx);

		if (result == null) {
			result = new IntoImpl(programUnit, ctx);

			final Call intoCall = createCall(ctx.qualifiedDataName());
			result.setIntoCall(intoCall);

			into = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AtEndPhrase getAtEndPhrase() {
		return atEndPhrase;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public Into getInto() {
		return into;
	}

	@Override
	public NotAtEndPhrase getNotAtEndPhrase() {
		return notAtEndPhrase;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setAtEndPhrase(final AtEndPhrase atEndPhrase) {
		this.atEndPhrase = atEndPhrase;
	}

	@Override
	public void setNotAtEndPhrase(final NotAtEndPhrase notAtEndPhrase) {
		this.notAtEndPhrase = notAtEndPhrase;
	}

}
