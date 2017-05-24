/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.AlphabetAlsoContext;
import io.proleap.cobol.Cobol85Parser.AlphabetClauseFormat1Context;
import io.proleap.cobol.Cobol85Parser.AlphabetLiteralsContext;
import io.proleap.cobol.Cobol85Parser.LiteralContext;
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
