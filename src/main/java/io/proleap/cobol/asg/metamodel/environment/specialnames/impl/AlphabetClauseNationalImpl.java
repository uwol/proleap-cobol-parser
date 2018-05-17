/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import io.proleap.cobol.CobolParser.AlphabetClauseFormat2Context;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.specialnames.AlphabetClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.AlphabetClauseNational;

public class AlphabetClauseNationalImpl extends AlphabetClauseImpl implements AlphabetClauseNational {

	protected AlphabetClauseNationalType alphabetClauseNationalType;

	protected Literal ccsVersionLiteral;

	protected final AlphabetClauseFormat2Context ctx;

	public AlphabetClauseNationalImpl(final ProgramUnit programUnit, final AlphabetClauseFormat2Context ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AlphabetClauseNationalType getAlphabetClauseNationalType() {
		return alphabetClauseNationalType;
	}

	@Override
	public Literal getCcsVersion() {
		return ccsVersionLiteral;
	}

	@Override
	public AlphabetClauseType getAlphabetClauseType() {
		return AlphabetClause.AlphabetClauseType.NATIONAL;
	}

	@Override
	public void setAlphabetClauseNationalType(final AlphabetClauseNationalType alphabetClauseNationalType) {
		this.alphabetClauseNationalType = alphabetClauseNationalType;
	}

	@Override
	public void setCcsVersionLiteral(final Literal ccsVersionLiteral) {
		this.ccsVersionLiteral = ccsVersionLiteral;
	}

}
