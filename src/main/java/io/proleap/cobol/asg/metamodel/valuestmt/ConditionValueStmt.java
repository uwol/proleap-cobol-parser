/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt;

import java.util.List;

import io.proleap.cobol.CobolParser.AndOrConditionContext;
import io.proleap.cobol.CobolParser.CombinableConditionContext;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.AndOrCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.CombinableCondition;

public interface ConditionValueStmt extends ValueStmt {

	AndOrCondition addAndOrCondition(AndOrConditionContext ctx);

	CombinableCondition addCombinableCondition(CombinableConditionContext ctx);

	List<AndOrCondition> getAndOrConditions();

	CombinableCondition getCombinableCondition();

}
