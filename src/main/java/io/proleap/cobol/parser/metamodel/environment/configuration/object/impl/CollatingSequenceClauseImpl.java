/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.configuration.object.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.CollatingSequenceClauseContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.configuration.object.CollatingSequenceClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class CollatingSequenceClauseImpl extends CobolDivisionElementImpl implements CollatingSequenceClause {

	protected List<String> alphabetNames = new ArrayList<String>();

	protected String alphanumeric;

	protected final CollatingSequenceClauseContext ctx;

	protected String national;

	public CollatingSequenceClauseImpl(final ProgramUnit programUnit, final CobolDivision scope,
			final CollatingSequenceClauseContext ctx) {
		super(programUnit, scope, ctx);

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
