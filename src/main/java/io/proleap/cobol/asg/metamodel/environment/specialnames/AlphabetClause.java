/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface AlphabetClause extends CobolDivisionElement {

	enum AlphabetClauseType {
		ALPHANUMERIC, NATIONAL
	}

	Call getAlphabetCall();

	AlphabetClauseType getAlphabetClauseType();

	void setAlphabetCall(Call alphabetCall);

}
