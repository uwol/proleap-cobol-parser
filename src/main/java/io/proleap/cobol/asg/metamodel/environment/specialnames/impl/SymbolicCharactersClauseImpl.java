/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import io.proleap.cobol.Cobol85Parser.SymbolicCharactersClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.specialnames.SymbolicCharactersClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class SymbolicCharactersClauseImpl extends CobolDivisionElementImpl implements SymbolicCharactersClause {

	protected final SymbolicCharactersClauseContext ctx;

	protected SymbolicCharactersClauseType symbolicCharactersClauseType;

	public SymbolicCharactersClauseImpl(final ProgramUnit programUnit, final SymbolicCharactersClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public SymbolicCharactersClauseType getSymbolicCharactersClauseType() {
		return symbolicCharactersClauseType;
	}

	@Override
	public void setSymbolicCharactersClauseType(final SymbolicCharactersClauseType symbolicCharactersClauseType) {
		this.symbolicCharactersClauseType = symbolicCharactersClauseType;
	}

}
