/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide.impl;

import io.proleap.cobol.CobolParser.DivideByGivingStatementContext;
import io.proleap.cobol.CobolParser.DivideGivingContext;
import io.proleap.cobol.CobolParser.DivideGivingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideByGivingStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.GivingPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ByGivingImpl extends CobolDivisionElementImpl implements DivideByGivingStatement {

	protected ValueStmt byValueStmt;

	protected DivideByGivingStatementContext ctx;

	protected GivingPhrase givingPhrase;

	public ByGivingImpl(final ProgramUnit programUnit, final DivideByGivingStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public GivingPhrase addGivingPhrase(final DivideGivingPhraseContext ctx) {
		GivingPhrase result = (GivingPhrase) getASGElement(ctx);

		if (result == null) {
			result = new GivingPhraseImpl(programUnit, ctx);

			// givings
			for (final DivideGivingContext divideGivingContext : ctx.divideGiving()) {
				result.addGiving(divideGivingContext);
			}

			givingPhrase = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt getByValueStmt() {
		return byValueStmt;
	}

	@Override
	public GivingPhrase getGivingPhrase() {
		return givingPhrase;
	}

	@Override
	public void setByValueStmt(final ValueStmt byValueStmt) {
		this.byValueStmt = byValueStmt;
	}
}
