/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.delete.impl;

import io.proleap.cobol.CobolParser.DeleteStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.delete.DeleteStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class DeleteStatementImpl extends StatementImpl implements DeleteStatement {

	protected final DeleteStatementContext ctx;

	protected Call fileCall;

	protected InvalidKeyPhrase invalidKeyPhrase;

	protected NotInvalidKeyPhrase notInvalidKeyPhrase;

	protected boolean record;

	protected final StatementType statementType = StatementTypeEnum.DELETE;

	public DeleteStatementImpl(final ProgramUnit programUnit, final Scope scope, final DeleteStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
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
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public boolean isRecord() {
		return record;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
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
	public void setRecord(final boolean record) {
		this.record = record;
	}

}
