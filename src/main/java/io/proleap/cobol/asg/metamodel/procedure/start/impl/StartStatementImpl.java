/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.start.impl;

import io.proleap.cobol.CobolParser.StartKeyContext;
import io.proleap.cobol.CobolParser.StartStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.start.Key;
import io.proleap.cobol.asg.metamodel.procedure.start.StartStatement;

public class StartStatementImpl extends StatementImpl implements StartStatement {

	protected final StartStatementContext ctx;

	protected Call fileCall;

	protected InvalidKeyPhrase invalidKeyPhrase;

	protected Key key;

	protected NotInvalidKeyPhrase notInvalidKeyPhrase;

	protected final StatementType statementType = StatementTypeEnum.START;

	public StartStatementImpl(final ProgramUnit programUnit, final Scope scope, final StartStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Key addKey(final StartKeyContext ctx) {
		Key result = (Key) getASGElement(ctx);

		if (result == null) {
			result = new KeyImpl(programUnit, ctx);

			// type
			final Key.KeyType type;

			if (ctx.MORETHANOREQUAL() != null) {
				type = Key.KeyType.GREATER_OR_EQUAL;
			} else if (ctx.GREATER() != null && ctx.EQUAL() != null) {
				type = Key.KeyType.GREATER_OR_EQUAL;
			}
			// with not
			else if (ctx.NOT() != null && ctx.LESSTHANCHAR() != null) {
				type = Key.KeyType.GREATER_OR_EQUAL;
			} else if (ctx.NOT() != null && ctx.LESS() != null) {
				type = Key.KeyType.GREATER_OR_EQUAL;
			}
			// without not
			else if (ctx.MORETHANCHAR() != null) {
				type = Key.KeyType.GREATER;
			} else if (ctx.GREATER() != null) {
				type = Key.KeyType.GREATER;
			} else if (ctx.EQUAL() != null) {
				type = Key.KeyType.EQUAL;
			} else if (ctx.EQUALCHAR() != null) {
				type = Key.KeyType.EQUAL;
			} else {
				type = null;
			}

			result.setKeyType(type);

			// comparison call
			if (ctx.qualifiedDataName() != null) {
				final Call comparisonCall = createCall(ctx.qualifiedDataName());
				result.setComparisonCall(comparisonCall);
			}

			key = result;
			registerASGElement(result);
		}

		return result;
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
	public Key getKey() {
		return key;
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
}
