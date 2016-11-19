/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.specialnames.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.AlphabetClauseFormat1Context;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.specialnames.AlphabetClauseAlphanumeric;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

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
	public AlphabetClauseAlphanumericType getAlphabetClauseType() {
		return alphabetClauseAlphanumericType;
	}

	@Override
	public List<ValueStmt> getCharacterSetValueStmts() {
		return characterSetValueStmts;
	}

	@Override
	public Type getType() {
		return Type.Alphanumeric;
	}

	@Override
	public void setAlphabetClauseAlphanumericType(final AlphabetClauseAlphanumericType alphabetClauseAlphanumericType) {
		this.alphabetClauseAlphanumericType = alphabetClauseAlphanumericType;
	}
}
