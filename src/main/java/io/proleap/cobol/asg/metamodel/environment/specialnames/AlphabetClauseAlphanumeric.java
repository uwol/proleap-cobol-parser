/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames;

import java.util.List;

import io.proleap.cobol.CobolParser.AlphabetLiteralsContext;
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
