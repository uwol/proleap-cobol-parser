/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.AlphabetAlsoContext;
import io.proleap.cobol.CobolParser.AlphabetClauseFormat1Context;
import io.proleap.cobol.CobolParser.AlphabetLiteralsContext;
import io.proleap.cobol.CobolParser.LiteralContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.specialnames.AlphabetClauseAlphanumeric;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class AlphabetClauseAlphanumericImpl extends AlphabetClauseImpl implements AlphabetClauseAlphanumeric {

	protected AlphabetClauseAlphanumericType alphabetClauseAlphanumericType;

	protected List<ValueStmt> characterSetValueStmts = new ArrayList<ValueStmt>();

	protected final AlphabetClauseFormat1Context ctx;

	public AlphabetClauseAlphanumericImpl(final ProgramUnit programUnit, final AlphabetClauseFormat1Context ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addCharacterSetValueStmt(final ValueStmt characterSetValueStmt) {
		characterSetValueStmts.add(characterSetValueStmt);
	}

	@Override
	public void addCharacterSetValueStmts(final AlphabetLiteralsContext ctx) {
		final ValueStmt characterSetValueStmt = createValueStmt(ctx.literal());
		characterSetValueStmts.add(characterSetValueStmt);

		/*
		 * through
		 */
		if (ctx.alphabetThrough() != null) {
			final ValueStmt characterSetThroughValueStmt = createValueStmt(ctx.alphabetThrough().literal());
			// TODO: add char sets in between
			characterSetValueStmts.add(characterSetThroughValueStmt);
		}

		/*
		 * also
		 */
		for (final AlphabetAlsoContext alphabetAlsoContext : ctx.alphabetAlso()) {
			final List<LiteralContext> literalContexts = alphabetAlsoContext.literal();

			for (final LiteralContext literalContext : literalContexts) {
				final ValueStmt characterSetAlsoValueStmt = createValueStmt(literalContext);
				characterSetValueStmts.add(characterSetAlsoValueStmt);
			}
		}
	}

	@Override
	public AlphabetClauseAlphanumericType getAlphabetClauseAlphanumericType() {
		return alphabetClauseAlphanumericType;
	}

	@Override
	public List<ValueStmt> getCharacterSetValueStmts() {
		return characterSetValueStmts;
	}

	@Override
	public AlphabetClauseType getAlphabetClauseType() {
		return AlphabetClauseType.ALPHANUMERIC;
	}

	@Override
	public void setAlphabetClauseAlphanumericType(final AlphabetClauseAlphanumericType alphabetClauseAlphanumericType) {
		this.alphabetClauseAlphanumericType = alphabetClauseAlphanumericType;
	}

}
