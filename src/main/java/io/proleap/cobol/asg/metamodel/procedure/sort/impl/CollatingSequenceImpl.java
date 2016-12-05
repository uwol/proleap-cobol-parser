/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.SortCollatingAlphanumericContext;
import io.proleap.cobol.Cobol85Parser.SortCollatingNationalContext;
import io.proleap.cobol.Cobol85Parser.SortCollatingSequencePhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.Alphanumeric;
import io.proleap.cobol.asg.metamodel.procedure.sort.CollatingSequence;
import io.proleap.cobol.asg.metamodel.procedure.sort.National;

public class CollatingSequenceImpl extends CobolDivisionElementImpl implements CollatingSequence {

	protected List<Call> alphabetCalls = new ArrayList<Call>();

	protected Alphanumeric alphanumeric;

	protected final SortCollatingSequencePhraseContext ctx;

	protected National national;

	public CollatingSequenceImpl(final ProgramUnit programUnit, final SortCollatingSequencePhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addAlphabetCall(final Call alphabetCall) {
		alphabetCalls.add(alphabetCall);
	}

	@Override
	public Alphanumeric addAlphanumeric(final SortCollatingAlphanumericContext ctx) {
		Alphanumeric result = (Alphanumeric) getASGElement(ctx);

		if (result == null) {
			result = new AlphanumericImpl(programUnit, ctx);

			// alphabet call
			final Call alphabetCall = createCall(ctx.alphabetName());
			result.setAlphabetCall(alphabetCall);

			alphanumeric = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public National addNational(final SortCollatingNationalContext ctx) {
		National result = (National) getASGElement(ctx);

		if (result == null) {
			result = new NationalImpl(programUnit, ctx);

			// alphabet call
			final Call alphabetCall = createCall(ctx.alphabetName());
			result.setAlphabetCall(alphabetCall);

			national = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Call> getAlphabetCalls() {
		return alphabetCalls;
	}

	@Override
	public Alphanumeric getAlphaNumeric() {
		return alphanumeric;
	}

	@Override
	public National getNational() {
		return national;
	}

}
