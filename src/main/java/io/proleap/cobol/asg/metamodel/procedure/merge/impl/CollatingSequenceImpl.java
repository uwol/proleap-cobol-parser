/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.merge.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.MergeCollatingAlphanumericContext;
import io.proleap.cobol.CobolParser.MergeCollatingNationalContext;
import io.proleap.cobol.CobolParser.MergeCollatingSequencePhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.merge.Alphanumeric;
import io.proleap.cobol.asg.metamodel.procedure.merge.CollatingSequencePhrase;
import io.proleap.cobol.asg.metamodel.procedure.merge.National;

public class CollatingSequenceImpl extends CobolDivisionElementImpl implements CollatingSequencePhrase {

	protected List<Call> alphabetCalls = new ArrayList<Call>();

	protected Alphanumeric alphanumeric;

	protected final MergeCollatingSequencePhraseContext ctx;

	protected National national;

	public CollatingSequenceImpl(final ProgramUnit programUnit, final MergeCollatingSequencePhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addAlphabetCall(final Call alphabetCall) {
		alphabetCalls.add(alphabetCall);
	}

	@Override
	public Alphanumeric addAlphanumeric(final MergeCollatingAlphanumericContext ctx) {
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
	public National addNational(final MergeCollatingNationalContext ctx) {
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
