/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.set.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.SetByValueContext;
import io.proleap.cobol.Cobol85Parser.SetToContext;
import io.proleap.cobol.Cobol85Parser.SetUpDownByStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.set.By;
import io.proleap.cobol.asg.metamodel.procedure.set.SetBy;
import io.proleap.cobol.asg.metamodel.procedure.set.To;

public class SetByImpl extends CobolDivisionElementImpl implements SetBy {

	protected By by;

	protected SetUpDownByStatementContext ctx;

	protected List<To> tos = new ArrayList<To>();

	protected Type type;

	public SetByImpl(final ProgramUnit programUnit, final SetUpDownByStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public By addBy(final SetByValueContext ctx) {
		By result = (By) getASGElement(ctx);

		if (result == null) {
			result = new ByImpl(programUnit, ctx);

			final Call byCall = createCall(ctx.identifier(), ctx.literal());
			result.setByCall(byCall);

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

			final Call toCall = createCall(ctx.identifier());
			result.setToCall(toCall);

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
	public List<To> getTos() {
		return tos;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
