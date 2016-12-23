/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.delete.impl;

import io.proleap.cobol.Cobol85Parser.DeleteStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKey;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKey;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.delete.DeleteStatement;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class DeleteStatementImpl extends StatementImpl implements DeleteStatement {

	protected final DeleteStatementContext ctx;

	protected Call fileCall;

	protected InvalidKey invalidKey;

	protected NotInvalidKey notInvalidKey;

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
	public InvalidKey getInvalidKey() {
		return invalidKey;
	}

	@Override
	public NotInvalidKey getNotInvalidKey() {
		return notInvalidKey;
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
	public void setInvalidKey(final InvalidKey invalidKey) {
		this.invalidKey = invalidKey;
	}

	@Override
	public void setNotInvalidKey(final NotInvalidKey notInvalidKey) {
		this.notInvalidKey = notInvalidKey;
	}

	@Override
	public void setRecord(final boolean record) {
		this.record = record;
	}

}
