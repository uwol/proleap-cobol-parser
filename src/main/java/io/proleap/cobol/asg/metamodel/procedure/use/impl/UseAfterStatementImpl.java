/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.use.impl;

import io.proleap.cobol.CobolParser.FileNameContext;
import io.proleap.cobol.CobolParser.UseAfterClauseContext;
import io.proleap.cobol.CobolParser.UseAfterOnContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.use.UseAfterStatement;
import io.proleap.cobol.asg.metamodel.procedure.use.AfterOn;

public class UseAfterStatementImpl extends CobolDivisionElementImpl implements UseAfterStatement {

	protected UseAfterClauseContext ctx;

	protected boolean global;

	protected AfterOn afterOn;

	public UseAfterStatementImpl(final ProgramUnit programUnit, final UseAfterClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AfterOn addAfterOn(final UseAfterOnContext ctx) {
		AfterOn result = (AfterOn) getASGElement(ctx);

		if (result == null) {
			result = new AfterOnImpl(programUnit, ctx);

			// type
			final AfterOn.AfterOnType type;

			if (!ctx.fileName().isEmpty()) {
				type = AfterOn.AfterOnType.FILE;
			} else if (ctx.INPUT() != null) {
				type = AfterOn.AfterOnType.INPUT;
			} else if (ctx.I_O() != null) {
				type = AfterOn.AfterOnType.INPUT_OUTPUT;
			} else if (ctx.OUTPUT() != null) {
				type = AfterOn.AfterOnType.OUTPUT;
			} else {
				type = null;
			}

			result.setType(type);

			// files
			for (final FileNameContext fileNameContext : ctx.fileName()) {
				final Call fileCall = createCall(fileNameContext);
				result.addFileCall(fileCall);
			}

			afterOn = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AfterOn getAfterOn() {
		return afterOn;
	}

	@Override
	public boolean isGlobal() {
		return global;
	}

	@Override
	public void setGlobal(final boolean global) {
		this.global = global;
	}

}
