/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.use.impl;

import io.proleap.cobol.Cobol85Parser.UseAfterClauseContext;
import io.proleap.cobol.Cobol85Parser.UseDebugClauseContext;
import io.proleap.cobol.Cobol85Parser.UseDebugOnContext;
import io.proleap.cobol.Cobol85Parser.UseStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.procedure.use.After;
import io.proleap.cobol.parser.metamodel.procedure.use.Debug;
import io.proleap.cobol.parser.metamodel.procedure.use.UseStatement;

public class UseStatementImpl extends CobolDivisionElementImpl implements UseStatement {

	protected final UseStatementContext ctx;

	protected Debug debug;

	protected After procedure;

	protected Type type;

	public UseStatementImpl(final ProgramUnit programUnit, final UseStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public After addAfter(final UseAfterClauseContext ctx) {
		After result = (After) getASGElement(ctx);

		if (result == null) {
			result = new AfterImpl(programUnit, ctx);

			// global
			if (ctx.GLOBAL() != null) {
				result.setGlobal(true);
			}

			// on
			result.addAfterOn(ctx.useAfterOn());

			procedure = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Debug addDebug(final UseDebugClauseContext ctx) {
		Debug result = (Debug) getASGElement(ctx);

		if (result == null) {
			result = new DebugImpl(programUnit, ctx);

			// debug on
			for (final UseDebugOnContext useDebugOnContext : ctx.useDebugOn()) {
				result.addDebugOn(useDebugOnContext);
			}

			debug = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public After getAfter() {
		return procedure;
	}

	@Override
	public Debug getDebug() {
		return debug;
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
