/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.start.impl;

import io.proleap.cobol.Cobol85Parser.StartKeyContext;
import io.proleap.cobol.Cobol85Parser.StartStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.Scope;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.InvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.NotInvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.start.Key;
import io.proleap.cobol.parser.metamodel.procedure.start.StartStatement;

public class StartStatementImpl extends StatementImpl implements StartStatement {

	protected final StartStatementContext ctx;

	protected Call fileCall;

	protected InvalidKey invalidKey;

	protected Key key;

	protected NotInvalidKey notInvalidKey;

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
			final Key.Type type;

			if (ctx.MORETHANOREQUAL() != null) {
				type = Key.Type.GreaterOrEqual;
			} else if (ctx.GREATER() != null && ctx.EQUAL() != null) {
				type = Key.Type.GreaterOrEqual;
			} else if (ctx.NOT() != null && ctx.LESSTHANCHAR() != null) {
				type = Key.Type.GreaterOrEqual;
			} else if (ctx.NOT() != null && ctx.LESS() != null) {
				type = Key.Type.GreaterOrEqual;
			} else if (ctx.MORETHANCHAR() != null) {
				type = Key.Type.Greater;
			} else if (ctx.GREATER() != null) {
				type = Key.Type.Greater;
			} else if (ctx.EQUAL() != null) {
				type = Key.Type.Equal;
			} else if (ctx.EQUALCHAR() != null) {
				type = Key.Type.Equal;
			} else {
				type = null;
			}

			// comparison call
			if (ctx.qualifiedDataName() != null) {
				final Call comparisonCall = createCall(ctx.qualifiedDataName());
				result.setComparisonCall(comparisonCall);
			}

			result.setType(type);

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
	public InvalidKey getInvalidKey() {
		return invalidKey;
	}

	@Override
	public Key getKey() {
		return key;
	}

	@Override
	public NotInvalidKey getNotInvalidKey() {
		return notInvalidKey;
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
}
