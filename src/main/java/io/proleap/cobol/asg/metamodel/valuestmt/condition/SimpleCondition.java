/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition;

import io.proleap.cobol.Cobol85Parser.ClassConditionContext;
import io.proleap.cobol.Cobol85Parser.ConditionContext;
import io.proleap.cobol.Cobol85Parser.ConditionNameReferenceContext;
import io.proleap.cobol.Cobol85Parser.RelationConditionContext;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.RelationConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface SimpleCondition extends ValueStmt {

	enum SimpleConditionType {
		CLASS_CONDITION, CONDITION, CONDITION_NAME_REFERENCE, RELATION_CONDITION
	}

	ClassCondition addClassCondition(ClassConditionContext ctx);

	ConditionValueStmt addCondition(ConditionContext ctx);

	ConditionNameReference addConditionNameReference(ConditionNameReferenceContext ctx);

	RelationConditionValueStmt addRelationCondition(RelationConditionContext ctx);

	ClassCondition getClassCondition();

	ConditionValueStmt getCondition();

	ConditionNameReference getConditionNameReference();

	RelationConditionValueStmt getRelationCondition();

	SimpleConditionType getSimpleConditionType();

	void setSimpleConditionType(SimpleConditionType simpleConditionType);

}
