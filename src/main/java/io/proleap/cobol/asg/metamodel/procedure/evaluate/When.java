/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.evaluate;

import java.util.List;

import io.proleap.cobol.CobolParser.EvaluateAlsoConditionContext;
import io.proleap.cobol.CobolParser.EvaluateConditionContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface When extends CobolDivisionElement {

	AlsoCondition addAlsoCondition(EvaluateAlsoConditionContext ctx);

	Condition addCondition(EvaluateConditionContext ctx);

	List<AlsoCondition> getAlsoConditions();

	Condition getCondition();
}
