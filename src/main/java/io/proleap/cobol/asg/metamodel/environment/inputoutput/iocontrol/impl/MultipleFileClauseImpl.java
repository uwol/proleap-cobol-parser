/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.MultipleFileClauseContext;
import io.proleap.cobol.CobolParser.MultipleFilePositionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.MultipleFileClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.MultipleFilePosition;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;

public class MultipleFileClauseImpl extends CobolDivisionElementImpl implements MultipleFileClause {

	protected final MultipleFileClauseContext ctx;

	protected List<MultipleFilePosition> multipleFilePositions = new ArrayList<MultipleFilePosition>();

	public MultipleFileClauseImpl(final ProgramUnit programUnit, final MultipleFileClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public MultipleFilePosition addMultipleFilePosition(final MultipleFilePositionContext ctx) {
		MultipleFilePosition result = (MultipleFilePosition) getASGElement(ctx);

		if (result == null) {
			result = new MultipleFilePositionImpl(programUnit, ctx);

			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			if (ctx.integerLiteral() != null) {
				final IntegerLiteralValueStmt integerLiteralValueStmt = createIntegerLiteralValueStmt(
						ctx.integerLiteral());
				result.setIntegerLiteralValueStmt(integerLiteralValueStmt);
			}

			multipleFilePositions.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<MultipleFilePosition> getMultipleFilePositions() {
		return multipleFilePositions;
	}

}
