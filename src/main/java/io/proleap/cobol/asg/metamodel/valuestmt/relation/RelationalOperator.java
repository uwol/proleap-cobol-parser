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

	enum Type {
		EQUAL, GREATER, GREATER_OR_EQUAL, LESS, LESS_OR_EQUAL, NOT_EQUAL
	}

	Type getType();

	void setType(Type type);

}
