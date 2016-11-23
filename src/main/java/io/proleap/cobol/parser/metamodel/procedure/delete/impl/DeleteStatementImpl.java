/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.delete.impl;

import io.proleap.cobol.Cobol85Parser.DeleteStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.parser.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.parser.metamodel.procedure.delete.DeleteStatement;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;

public class DeleteStatementImpl extends StatementImpl implements DeleteStatement {

	protected final DeleteStatementContext ctx;

	protected Call fileCall;

	protected InvalidKeyPhrase invalidKeyPhrase;

	protected NotInvalidKeyPhrase notInvalidKeyPhrase;

	protected boolean record;

	public DeleteStatementImpl(final ProgramUnit programUnit, final DeleteStatementContext ctx) {
		super(programUnit, ctx);

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
