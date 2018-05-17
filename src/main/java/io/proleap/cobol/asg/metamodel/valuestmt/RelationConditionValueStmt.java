/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt;

import io.proleap.cobol.CobolParser.RelationArithmeticComparisonContext;
import io.proleap.cobol.CobolParser.RelationCombinedComparisonContext;
import io.proleap.cobol.CobolParser.RelationSignConditionContext;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.ArithmeticComparison;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.CombinedComparison;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.SignCondition;

public interface RelationConditionValueStmt extends ValueStmt {

	enum RelationConditionType {
		ARITHMETIC, COMBINED, SIGN
	}

	ArithmeticComparison addRelationArithmeticComparison(RelationArithmeticComparisonContext ctx);

	CombinedComparison addRelationCombinedComparison(RelationCombinedComparisonContext ctx);

	SignCondition addRelationSignCondition(RelationSignConditionContext ctx);

	ArithmeticComparison getArithmeticComparison();

	CombinedComparison getCombinedComparison();

	SignCondition getSignCondition();

	RelationConditionType getRelationConditionType();

	void setRelationConditionType(RelationConditionType relationConditionType);
}
