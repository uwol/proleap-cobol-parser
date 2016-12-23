/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import io.proleap.cobol.Cobol85Parser.AlphabetClauseFormat2Context;
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
	public AlphabetClauseNationalType getAlphabetClauseType() {
		return alphabetClauseNationalType;
	}

	@Override
	public Literal getCcsVersion() {
		return ccsVersionLiteral;
	}

	@Override
	public Type getType() {
		return AlphabetClause.Type.NATIONAL;
	}

	@Override
	public void setAlphabetClauseType(final AlphabetClauseNationalType alphabetClauseNationalType) {
		this.alphabetClauseNationalType = alphabetClauseNationalType;
	}

	@Override
	public void setCcsVersionLiteral(final Literal ccsVersionLiteral) {
		this.ccsVersionLiteral = ccsVersionLiteral;
	}

}
