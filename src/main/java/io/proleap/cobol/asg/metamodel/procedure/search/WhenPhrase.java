/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.search;

import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;

public interface WhenPhrase extends Scope {

	enum WhenType {
		NEXT_SENTENCE, STATEMENTS
	}

	ConditionValueStmt getCondition();

	WhenType getWhenType();

	void setCondition(ConditionValueStmt condition);

	void setWhenType(WhenType whenType);
}
