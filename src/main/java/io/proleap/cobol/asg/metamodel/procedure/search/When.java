/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.search;

import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;

public interface When extends Scope {

	enum WhenType {
		NEXT_SENTENCE, STATEMENTS
	}

	ConditionValueStmt getCondition();

	WhenType getWhenType();

	void setCondition(ConditionValueStmt condition);

	void setWhenType(WhenType whenType);

}
