/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt;

import io.proleap.cobol.Cobol85Parser.RelationArithmeticComparisonContext;
import io.proleap.cobol.Cobol85Parser.RelationCombinedComparisonContext;
import io.proleap.cobol.Cobol85Parser.RelationSignConditionContext;
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
