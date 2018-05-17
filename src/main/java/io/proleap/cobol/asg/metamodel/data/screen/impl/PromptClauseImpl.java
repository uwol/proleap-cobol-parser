/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.CobolParser.ScreenDescriptionPromptClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionPromptOccursClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.Occurs;
import io.proleap.cobol.asg.metamodel.data.screen.PromptClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class PromptClauseImpl extends CobolDivisionElementImpl implements PromptClause {

	protected ValueStmt characterValueStmt;

	protected ScreenDescriptionPromptClauseContext ctx;

	protected Occurs occurs;

	public PromptClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionPromptClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Occurs addOccurs(final ScreenDescriptionPromptOccursClauseContext ctx) {
		Occurs result = (Occurs) getASGElement(ctx);

		if (result == null) {
			result = new OccursImpl(programUnit, ctx);

			if (ctx.integerLiteral() != null) {
				final IntegerLiteral occursTimes = createIntegerLiteral(ctx.integerLiteral());
				result.setOccursTimes(occursTimes);
			}

			occurs = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt getCharacterValueStmt() {
		return characterValueStmt;
	}

	@Override
	public Occurs getOccurs() {
		return occurs;
	}

	@Override
	public void setCharacterValueStmt(final ValueStmt characterValueStmt) {
		this.characterValueStmt = characterValueStmt;
	}

}
