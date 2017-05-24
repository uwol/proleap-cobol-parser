/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation;

import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface RelationalOperator extends ValueStmt {

	enum RelationalOperatorType {
		EQUAL, GREATER, GREATER_OR_EQUAL, LESS, LESS_OR_EQUAL, NOT_EQUAL
	}

	RelationalOperatorType getRelationalOperatorType();

	void setRelationalOperatorType(RelationalOperatorType type);

}
