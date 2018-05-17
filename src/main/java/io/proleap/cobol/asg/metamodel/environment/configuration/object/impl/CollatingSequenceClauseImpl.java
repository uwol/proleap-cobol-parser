/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.configuration.object.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.CollatingSequenceClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.CollatingSequenceClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class CollatingSequenceClauseImpl extends CobolDivisionElementImpl implements CollatingSequenceClause {

	protected List<String> alphabetNames = new ArrayList<String>();

	protected String alphanumeric;

	protected final CollatingSequenceClauseContext ctx;

	protected String national;

	public CollatingSequenceClauseImpl(final ProgramUnit programUnit, final CollatingSequenceClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addAlphabetName(final String alphabetName) {
		alphabetNames.add(alphabetName);
	}

	@Override
	public List<String> getAlphabetNames() {
		return alphabetNames;
	}

	@Override
	public String getAlphaNumeric() {
		return alphanumeric;
	}

	@Override
	public String getNational() {
		return national;
	}

	@Override
	public void setAlphaNumeric(final String alphanumeric) {
		this.alphanumeric = alphanumeric;
	}

	@Override
	public void setNational(final String national) {
		this.national = national;
	}

}
