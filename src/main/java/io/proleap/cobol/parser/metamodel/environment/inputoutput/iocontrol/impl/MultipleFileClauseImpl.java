/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.MultipleFileClauseContext;
import io.proleap.cobol.Cobol85Parser.MultipleFilePositionClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.MultipleFileClause;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.MultipleFilePositionClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class MultipleFileClauseImpl extends CobolDivisionElementImpl implements MultipleFileClause {

	protected final MultipleFileClauseContext ctx;

	protected List<MultipleFilePositionClause> multipleFilePositionClauses = new ArrayList<MultipleFilePositionClause>();

	public MultipleFileClauseImpl(final ProgramUnit programUnit, final MultipleFileClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public MultipleFilePositionClause addMultipleFilePositionClause(final MultipleFilePositionClauseContext ctx) {
		MultipleFilePositionClause result = (MultipleFilePositionClause) getASGElement(ctx);

		if (result == null) {
			result = new MultipleFilePositionClauseImpl(programUnit, ctx);

			final ValueStmt fileNameValueStmt = createCallValueStmt(ctx.fileName());
			result.setFileNameValueStmt(fileNameValueStmt);

			if (ctx.integerLiteral() != null) {
				final IntegerLiteralValueStmt integerLiteralValueStmt = createIntegerLiteralValueStmt(
						ctx.integerLiteral());
				result.setIntegerLiteralValueStmt(integerLiteralValueStmt);
			}

			multipleFilePositionClauses.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<MultipleFilePositionClause> getMultipleFilePositionClauses() {
		return multipleFilePositionClauses;
	}

}
