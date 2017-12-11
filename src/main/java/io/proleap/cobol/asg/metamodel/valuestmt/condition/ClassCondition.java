/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition;

import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface ClassCondition extends ValueStmt {

	enum ClassConditionType {
		ALPHABETIC, ALPHABETIC_LOWER, ALPHABETIC_UPPER, CLASS_NAME, DBCS, KANJI, NUMERIC
	}

	Call getClassCall();

	Call getIdentifierCall();

	boolean getNot();

	ClassConditionType getClassConditionType();

	void setClassCall(Call classCall);

	void setIdentifierCall(Call identifierCall);

	void setNot(boolean not);

	void setClassConditionType(ClassConditionType classConditionType);

}
