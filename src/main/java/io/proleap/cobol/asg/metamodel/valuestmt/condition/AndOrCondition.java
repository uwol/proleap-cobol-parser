/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.AbbreviationContext;
import io.proleap.cobol.Cobol85Parser.CombinableConditionContext;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.Abbreviation;

public interface AndOrCondition extends ValueStmt {

	enum Type {
		AND, OR
	}

	Abbreviation addAbbreviation(AbbreviationContext abbreviationRest);

	CombinableCondition addCombinableCondition(CombinableConditionContext ctx);

	List<Abbreviation> getAbbreviations();

	CombinableCondition getCombinableCondition();

	Type getType();

	void setType(Type type);

}
