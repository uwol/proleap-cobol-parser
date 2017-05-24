/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.AlphabetLiteralsContext;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface AlphabetClauseAlphanumeric extends AlphabetClause {

	enum AlphabetClauseAlphanumericType {
		ASCII, EBCDIC, NATIVE, STANDARD_1, STANDARD_2
	}

	void addCharacterSetValueStmt(ValueStmt characterSetValueStmt);

	void addCharacterSetValueStmts(AlphabetLiteralsContext ctx);

	AlphabetClauseAlphanumericType getAlphabetClauseAlphanumericType();

	List<ValueStmt> getCharacterSetValueStmts();

	void setAlphabetClauseAlphanumericType(AlphabetClauseAlphanumericType alphabetClauseAlphanumericType);
}
