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
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.specialnames.AlphabetClauseAlphanumeric;

public class AlphabetClauseAlphanumericImpl extends AlphabetClauseImpl implements AlphabetClauseAlphanumeric {

	protected AlphabetClauseAlphanumericType alphabetClauseAlphanumericType;

	protected List<Call> characterSetCalls = new ArrayList<Call>();

	protected final AlphabetClauseFormat1Context ctx;

	public AlphabetClauseAlphanumericImpl(final ProgramUnit programUnit, final AlphabetClauseFormat1Context ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addCharacterSetCall(final Call characterSetValueStmt) {
		characterSetCalls.add(characterSetValueStmt);
	}

	@Override
	public void addCharacterSetCalls(final AlphabetLiteralsContext ctx) {
		final Call characterSetCall = createCall(ctx.literal());
		characterSetCalls.add(characterSetCall);

		/*
		 * through
		 */
		if (ctx.alphabetThrough() != null) {
			final Call characterSetThroughCall = createCall(ctx.alphabetThrough().literal());
			// TODO: add char sets in between
			characterSetCalls.add(characterSetThroughCall);
		}

		/*
		 * also
		 */
		for (final AlphabetAlsoContext alphabetAlsoContext : ctx.alphabetAlso()) {
			final List<LiteralContext> literalContexts = alphabetAlsoContext.literal();

			for (final LiteralContext literalContext : literalContexts) {
				final Call characterSetAlsoCall = createCall(literalContext);
				characterSetCalls.add(characterSetAlsoCall);
			}
		}
	}

	@Override
	public AlphabetClauseAlphanumericType getAlphabetClauseType() {
		return alphabetClauseAlphanumericType;
	}

	@Override
	public List<Call> getCharacterSetCalls() {
		return characterSetCalls;
	}

	@Override
	public Type getType() {
		return Type.ALPHANUMERIC;
	}

	@Override
	public void setAlphabetClauseAlphanumericType(final AlphabetClauseAlphanumericType alphabetClauseAlphanumericType) {
		this.alphabetClauseAlphanumericType = alphabetClauseAlphanumericType;
	}

}
