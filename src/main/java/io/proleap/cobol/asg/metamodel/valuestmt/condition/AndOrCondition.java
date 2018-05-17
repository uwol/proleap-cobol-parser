/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition;

import java.util.List;

import io.proleap.cobol.CobolParser.AbbreviationContext;
import io.proleap.cobol.CobolParser.CombinableConditionContext;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.Abbreviation;

public interface AndOrCondition extends ValueStmt {

	enum AndOrConditionType {
		AND, OR
	}

	Abbreviation addAbbreviation(AbbreviationContext abbreviationRest);

	CombinableCondition addCombinableCondition(CombinableConditionContext ctx);

	List<Abbreviation> getAbbreviations();

	CombinableCondition getCombinableCondition();

	AndOrConditionType getAndOrConditionType();

	void setAndOrConditionType(AndOrConditionType andOrConditionType);

}
