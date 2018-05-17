/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.set.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.SetStatementContext;
import io.proleap.cobol.CobolParser.SetToContext;
import io.proleap.cobol.CobolParser.SetToStatementContext;
import io.proleap.cobol.CobolParser.SetToValueContext;
import io.proleap.cobol.CobolParser.SetUpDownByStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.set.SetBy;
import io.proleap.cobol.asg.metamodel.procedure.set.SetStatement;
import io.proleap.cobol.asg.metamodel.procedure.set.SetTo;

public class SetStatementImpl extends StatementImpl implements SetStatement {

	protected final SetStatementContext ctx;

	protected SetBy setBy;

	protected List<SetTo> setTos = new ArrayList<SetTo>();

	protected SetType setType;

	protected final StatementType statementType = StatementTypeEnum.SET;

	public SetStatementImpl(final ProgramUnit programUnit, final Scope scope, final SetStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public SetBy addSetBy(final SetUpDownByStatementContext ctx) {
		SetBy result = (SetBy) getASGElement(ctx);

		if (result == null) {
			result = new SetByImpl(programUnit, ctx);

			// type
			final SetBy.SetByType type;

			if (ctx.UP() != null) {
				type = SetBy.SetByType.UP;
			} else if (ctx.DOWN() != null) {
				type = SetBy.SetByType.DOWN;
			} else {
				type = null;
			}

			result.setSetByType(type);

			// to
			for (final SetToContext setToContext : ctx.setTo()) {
				result.addTo(setToContext);
			}

			// by
			result.addBy(ctx.setByValue());

			setBy = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SetTo addSetTo(final SetToStatementContext ctx) {
		SetTo result = (SetTo) getASGElement(ctx);

		if (result == null) {
			result = new SetToImpl(programUnit, ctx);

			// to
			for (final SetToContext setToContext : ctx.setTo()) {
				result.addTo(setToContext);
			}

			// by
			for (final SetToValueContext setToValueContext : ctx.setToValue()) {
				result.addValue(setToValueContext);
			}

			setTos.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SetBy getSetBy() {
		return setBy;
	}

	@Override
	public List<SetTo> getSetTos() {
		return setTos;
	}

	@Override
	public SetType getSetType() {
		return setType;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setSetType(final SetType setType) {
		this.setType = setType;
	}

}
