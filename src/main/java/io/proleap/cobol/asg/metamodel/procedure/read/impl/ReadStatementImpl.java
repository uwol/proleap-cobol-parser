/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.read.impl;

import io.proleap.cobol.CobolParser.ReadIntoContext;
import io.proleap.cobol.CobolParser.ReadKeyContext;
import io.proleap.cobol.CobolParser.ReadStatementContext;
import io.proleap.cobol.CobolParser.ReadWithContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.AtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotAtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.read.Into;
import io.proleap.cobol.asg.metamodel.procedure.read.Key;
import io.proleap.cobol.asg.metamodel.procedure.read.ReadStatement;
import io.proleap.cobol.asg.metamodel.procedure.read.With;

public class ReadStatementImpl extends StatementImpl implements ReadStatement {

	protected AtEndPhrase atEnd;

	protected final ReadStatementContext ctx;

	protected Call fileCall;

	protected Into into;

	protected InvalidKeyPhrase invalidKeyPhrase;

	protected Key key;

	protected boolean nextRecord;

	protected NotAtEndPhrase notAtEndPhrase;

	protected NotInvalidKeyPhrase notInvalidKeyPhrase;

	protected final StatementType statementType = StatementTypeEnum.READ;

	protected With with;

	public ReadStatementImpl(final ProgramUnit programUnit, final Scope scope, final ReadStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Into addInto(final ReadIntoContext ctx) {
		Into result = (Into) getASGElement(ctx);

		if (result == null) {
			result = new IntoImpl(programUnit, ctx);

			if (ctx.identifier() != null) {
				final Call intoCall = createCall(ctx.identifier());
				result.setIntoCall(intoCall);
			}

			into = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Key addKey(final ReadKeyContext ctx) {
		Key result = (Key) getASGElement(ctx);

		if (result == null) {
			result = new KeyImpl(programUnit, ctx);

			final Call keyCall = createCall(ctx.qualifiedDataName());
			result.setKeyCall(keyCall);

			key = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public With addWith(final ReadWithContext ctx) {
		With result = (With) getASGElement(ctx);

		if (result == null) {
			result = new WithImpl(programUnit, ctx);

			// type
			final With.WithType type;

			if (ctx.KEPT() != null) {
				type = With.WithType.KEPT_LOCK;
			} else if (ctx.NO() != null) {
				type = With.WithType.NO_LOCK;
			} else if (ctx.WAIT() != null) {
				type = With.WithType.WAIT;
			} else {
				type = null;
			}

			result.setWithType(type);

			with = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AtEndPhrase getAtEnd() {
		return atEnd;
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
	public InvalidKeyPhrase getInvalidKeyPhrase() {
		return invalidKeyPhrase;
	}

	@Override
	public Key getKey() {
		return key;
	}

	@Override
	public NotAtEndPhrase getNotAtEndPhrase() {
		return notAtEndPhrase;
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
	public With getWith() {
		return with;
	}

	@Override
	public boolean isNextRecord() {
		return nextRecord;
	}

	@Override
	public void setAtEnd(final AtEndPhrase atEnd) {
		this.atEnd = atEnd;
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
	public void setNextRecord(final boolean nextRecord) {
		this.nextRecord = nextRecord;
	}

	@Override
	public void setNotAtEndPhrase(final NotAtEndPhrase notAtEndPhrase) {
		this.notAtEndPhrase = notAtEndPhrase;
	}

	@Override
	public void setNotInvalidKeyPhrase(final NotInvalidKeyPhrase notInvalidKeyPhrase) {
		this.notInvalidKeyPhrase = notInvalidKeyPhrase;
	}
}
