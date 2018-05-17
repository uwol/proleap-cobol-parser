/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition;

import io.proleap.cobol.CobolParser.ClassConditionContext;
import io.proleap.cobol.CobolParser.ConditionContext;
import io.proleap.cobol.CobolParser.ConditionNameReferenceContext;
import io.proleap.cobol.CobolParser.RelationConditionContext;
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
