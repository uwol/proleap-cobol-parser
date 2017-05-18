/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide.impl;

import io.proleap.cobol.Cobol85Parser.DivideByGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideGivingContext;
import io.proleap.cobol.Cobol85Parser.DivideGivingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.divide.ByGiving;
import io.proleap.cobol.asg.metamodel.procedure.divide.Givings;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ByGivingImpl extends CobolDivisionElementImpl implements ByGiving {

	protected ValueStmt byValueStmt;

	protected DivideByGivingStatementContext ctx;

	protected Givings givings;

	public ByGivingImpl(final ProgramUnit programUnit, final DivideByGivingStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Givings addGivings(final DivideGivingPhraseContext ctx) {
		Givings result = (Givings) getASGElement(ctx);

		if (result == null) {
			result = new GivingsImpl(programUnit, ctx);

			// givings
			for (final DivideGivingContext divideGivingContext : ctx.divideGiving()) {
				result.addGiving(divideGivingContext);
			}

			givings = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt getByValueStmt() {
		return byValueStmt;
	}

	@Override
	public Givings getGivings() {
		return givings;
	}

	@Override
	public void setByValueStmt(final ValueStmt byValueStmt) {
		this.byValueStmt = byValueStmt;
	}

}
