/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionFromClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionToClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.screen.FromClause;
import io.proleap.cobol.asg.metamodel.data.screen.To;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class FromClauseImpl extends CobolDivisionElementImpl implements FromClause {

	protected ScreenDescriptionFromClauseContext ctx;

	protected ValueStmt fromValueStmt;

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

			if (ctx.identifier() != null) {
				final Call toCall = createCall(ctx.identifier());
				result.setToCall(toCall);
			}

			to = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt getFromValueStmt() {
		return fromValueStmt;
	}

	@Override
	public To getTo() {
		return to;
	}

	@Override
	public void setFromValueStmt(final ValueStmt fromValueStmt) {
		this.fromValueStmt = fromValueStmt;
	}

}
