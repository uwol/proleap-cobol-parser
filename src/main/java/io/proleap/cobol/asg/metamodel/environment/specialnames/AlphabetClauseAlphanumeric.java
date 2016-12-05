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
import io.proleap.cobol.asg.metamodel.call.Call;

public interface AlphabetClauseAlphanumeric extends AlphabetClause {

	enum AlphabetClauseAlphanumericType {
		Ascii, Ebcdic, Native, Standard1, Standard2
	}

	void addCharacterSetCall(Call characterSetValueStmt);

	void addCharacterSetCalls(AlphabetLiteralsContext ctx);

	AlphabetClauseAlphanumericType getAlphabetClauseType();

	List<Call> getCharacterSetCalls();

	void setAlphabetClauseAlphanumericType(AlphabetClauseAlphanumericType alphabetClauseAlphanumericType);
}
