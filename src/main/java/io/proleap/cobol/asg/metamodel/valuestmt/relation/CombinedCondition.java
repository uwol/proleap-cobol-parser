/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation;

import java.util.List;

import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface CombinedCondition extends ValueStmt {

	enum CombinedConditionType {
		AND, OR
	}

	void addArithmeticExpression(ArithmeticValueStmt arithmeticExpression);

	List<ArithmeticValueStmt> getArithmeticExpressions();

	CombinedConditionType getCombinedConditionType();

	void setCombinedConditionType(CombinedConditionType combinedConditionType);

}
