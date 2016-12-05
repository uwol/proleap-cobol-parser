/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames;

import io.proleap.cobol.asg.metamodel.Literal;

public interface AlphabetClauseNational extends AlphabetClause {

	enum AlphabetClauseNationalType {
		CcsVersion, Native
	}

	AlphabetClauseNationalType getAlphabetClauseType();

	Literal getCcsVersion();

	void setAlphabetClauseType(AlphabetClauseNationalType alphabetClauseNationalType);

	void setCcsVersionLiteral(Literal ccsVersionLiteral);
}
