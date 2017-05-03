/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.AndOrConditionContext;
import io.proleap.cobol.Cobol85Parser.CombinableConditionContext;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.AndOrCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.CombinableCondition;

public interface ConditionValueStmt extends ValueStmt {

	AndOrCondition addAndOrCondition(AndOrConditionContext ctx);

	CombinableCondition addCombinableCondition(CombinableConditionContext ctx);

	List<AndOrCondition> getAndOrConditions();

	CombinableCondition getCombinableCondition();

}
