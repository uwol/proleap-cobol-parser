/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.subtract.impl;

import io.proleap.cobol.Cobol85Parser.SubtractCorrespondingStatementContext;
import io.proleap.cobol.Cobol85Parser.SubtractFromGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.SubtractFromStatementContext;
import io.proleap.cobol.Cobol85Parser.SubtractGivingContext;
import io.proleap.cobol.Cobol85Parser.SubtractMinuendContext;
import io.proleap.cobol.Cobol85Parser.SubtractStatementContext;
import io.proleap.cobol.Cobol85Parser.SubtractSubtrahendContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.parser.metamodel.procedure.OnSizeError;
import io.proleap.cobol.parser.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.subtract.SubtractCorresponding;
import io.proleap.cobol.parser.metamodel.procedure.subtract.SubtractFrom;
import io.proleap.cobol.parser.metamodel.procedure.subtract.SubtractFromGiving;
import io.proleap.cobol.parser.metamodel.procedure.subtract.SubtractStatement;

public class SubtractStatementImpl extends StatementImpl implements SubtractStatement {

	protected final SubtractStatementContext ctx;

	protected NotOnSizeError notOnSizeError;

	protected OnSizeError onSizeError;

	protected SubtractCorresponding subtractCorresponding;

	protected SubtractFrom subtractFrom;

	protected SubtractFromGiving subtractFromGiving;

	protected Type type;

	public SubtractStatementImpl(final ProgramUnit programUnit, final SubtractStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public SubtractCorresponding addSubtractCorresponding(final SubtractCorrespondingStatementContext ctx) {
		SubtractCorresponding result = (SubtractCorresponding) getASGElement(ctx);

		if (result == null) {
			result = new SubtractCorrespondingImpl(programUnit, ctx);

			// subtrahend call
			final Call subtrahendCall = createCall(ctx.qualifiedDataName());
			result.setSubtrahendCall(subtrahendCall);

			// minuend
			result.addMinuend(ctx.subtractMinuendCorresponding());

			subtractCorresponding = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SubtractFrom addSubtractFrom(final SubtractFromStatementContext ctx) {
		SubtractFrom result = (SubtractFrom) getASGElement(ctx);

		if (result == null) {
			result = new SubtractFromImpl(programUnit, ctx);

			// subtrahends
			for (final SubtractSubtrahendContext subtractSubtrahendContext : ctx.subtractSubtrahend()) {
				result.addSubtrahend(subtractSubtrahendContext);
			}

			// minuends
			for (final SubtractMinuendContext subtractMinuendContext : ctx.subtractMinuend()) {
				result.addMinuend(subtractMinuendContext);
			}

			subtractFrom = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SubtractFromGiving addSubtractFromGiving(final SubtractFromGivingStatementContext ctx) {
		SubtractFromGiving result = (SubtractFromGiving) getASGElement(ctx);

		if (result == null) {
			result = new SubtractFromGivingImpl(programUnit, ctx);

			// subtrahends
			for (final SubtractSubtrahendContext subtractSubtrahendContext : ctx.subtractSubtrahend()) {
				result.addSubtrahend(subtractSubtrahendContext);
			}

			// minuend
			result.addMinuend(ctx.subtractMinuendGiving());

			// giving
			for (final SubtractGivingContext subtractGivingContext : ctx.subtractGiving()) {
				result.addGiving(subtractGivingContext);
			}

			subtractFromGiving = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public NotOnSizeError getNotOnSizeError() {
		return notOnSizeError;
	}

	@Override
	public OnSizeError getOnSizeError() {
		return onSizeError;
	}

	@Override
	public SubtractCorresponding getSubtractCorresponding() {
		return subtractCorresponding;
	}

	@Override
	public SubtractFrom getSubtractFrom() {
		return subtractFrom;
	}

	@Override
	public SubtractFromGiving getSubtractFromGiving() {
		return subtractFromGiving;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setNotOnSizeError(final NotOnSizeError notOnSizeError) {
		this.notOnSizeError = notOnSizeError;
	}

	@Override
	public void setOnSizeError(final OnSizeError onSizeError) {
		this.onSizeError = onSizeError;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
