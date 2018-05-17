/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.InspectAllLeadingContext;
import io.proleap.cobol.CobolParser.InspectBeforeAfterContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.inspect.AllLeading;
import io.proleap.cobol.asg.metamodel.procedure.inspect.BeforeAfterPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class AllLeadingImpl extends InspectPhraseImpl implements AllLeading {

	protected List<BeforeAfterPhrase> beforeAfterPhrases = new ArrayList<BeforeAfterPhrase>();

	protected final InspectAllLeadingContext ctx;

	protected ValueStmt patternDataItemValueStmt;

	public AllLeadingImpl(final ProgramUnit programUnit, final InspectAllLeadingContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public BeforeAfterPhrase addBeforeAfterPhrase(final InspectBeforeAfterContext ctx) {
		BeforeAfterPhrase result = (BeforeAfterPhrase) getASGElement(ctx);

		if (result == null) {
			result = createBeforeAfterPhrase(ctx);
			beforeAfterPhrases.add(result);
		}

		return result;
	}

	@Override
	public List<BeforeAfterPhrase> getBeforeAfterPhrases() {
		return beforeAfterPhrases;
	}

	@Override
	public ValueStmt getPatternDataItemValueStmt() {
		return patternDataItemValueStmt;
	}

	@Override
	public void setPatternDataItemValueStmt(final ValueStmt patternDataItemValueStmt) {
		this.patternDataItemValueStmt = patternDataItemValueStmt;
	}

}
