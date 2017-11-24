/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide.impl;

import io.proleap.cobol.Cobol85Parser.DivideGivingContext;
import io.proleap.cobol.Cobol85Parser.DivideGivingPhraseContext;
import io.proleap.cobol.Cobol85Parser.DivideIntoStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideIntoStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.GivingPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class DivideIntoStatementImpl extends CobolDivisionElementImpl implements DivideIntoStatement {

	protected DivideIntoStatementContext ctx;

	protected GivingPhrase givingPhrase;

	protected ValueStmt intoValueStmt;

	public DivideIntoStatementImpl(final ProgramUnit programUnit, final DivideIntoStatementContext ctx) {
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
	public GivingPhrase getGivingPhrase() {
		return givingPhrase;
	}

	@Override
	public ValueStmt getIntoValueStmt() {
		return intoValueStmt;
	}

	@Override
	public void setIntoValueStmt(final ValueStmt intoValueStmt) {
		this.intoValueStmt = intoValueStmt;
	}
}
