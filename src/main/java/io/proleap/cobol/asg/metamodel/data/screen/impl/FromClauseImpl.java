/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionFromClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionToClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.screen.FromClause;
import io.proleap.cobol.asg.metamodel.data.screen.To;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class FromClauseImpl extends CobolDivisionElementImpl implements FromClause {

	protected ScreenDescriptionFromClauseContext ctx;

	protected Call fromCall;

	protected To to;

	public FromClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionFromClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public To addTo(final ScreenDescriptionToClauseContext ctx) {
		To result = (To) getASGElement(ctx);

		if (result == null) {
			result = new ToImpl(programUnit, ctx);

			final Call toCall = createCall(ctx.identifier());
			result.setToCall(toCall);

			to = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call getFromCall() {
		return fromCall;
	}

	@Override
	public To getTo() {
		return to;
	}

	@Override
	public void setFromCall(final Call fromCall) {
		this.fromCall = fromCall;
	}

}
