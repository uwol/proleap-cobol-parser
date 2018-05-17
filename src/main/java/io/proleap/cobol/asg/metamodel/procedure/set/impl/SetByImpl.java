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

import io.proleap.cobol.CobolParser.SetByValueContext;
import io.proleap.cobol.CobolParser.SetToContext;
import io.proleap.cobol.CobolParser.SetUpDownByStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.set.By;
import io.proleap.cobol.asg.metamodel.procedure.set.SetBy;
import io.proleap.cobol.asg.metamodel.procedure.set.To;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class SetByImpl extends CobolDivisionElementImpl implements SetBy {

	protected By by;

	protected SetUpDownByStatementContext ctx;

	protected SetByType setByType;

	protected List<To> tos = new ArrayList<To>();

	public SetByImpl(final ProgramUnit programUnit, final SetUpDownByStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public By addBy(final SetByValueContext ctx) {
		By result = (By) getASGElement(ctx);

		if (result == null) {
			result = new ByImpl(programUnit, ctx);

			final ValueStmt byValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setByValueStmt(byValueStmt);

			by = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public To addTo(final SetToContext ctx) {
		To result = (To) getASGElement(ctx);

		if (result == null) {
			result = new ToImpl(programUnit, ctx);

			if (ctx.identifier() != null) {
				final Call toCall = createCall(ctx.identifier());
				result.setToCall(toCall);
			}

			tos.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public By getBy() {
		return by;
	}

	@Override
	public SetByType getSetByType() {
		return setByType;
	}

	@Override
	public List<To> getTos() {
		return tos;
	}

	@Override
	public void setSetByType(final SetByType setByType) {
		this.setByType = setByType;
	}

}
