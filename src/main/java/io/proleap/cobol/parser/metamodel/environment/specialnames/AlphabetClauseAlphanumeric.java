/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.specialnames;

import java.util.List;

import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface AlphabetClauseAlphanumeric extends AlphabetClause {

	enum AlphabetClauseAlphanumericType {
		Ascii, Ebcdic, Native, Standard1, Standard2
	}

	void addCharacterSetValueStmt(ValueStmt characterSetValueStmt);

	AlphabetClauseAlphanumericType getAlphabetClauseType();

	List<ValueStmt> getCharacterSetValueStmts();

	void setAlphabetClauseAlphanumericType(AlphabetClauseAlphanumericType alphabetClauseAlphanumericType);
}
