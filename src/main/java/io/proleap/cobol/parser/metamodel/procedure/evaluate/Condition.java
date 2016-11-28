/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.evaluate;

import io.proleap.cobol.Cobol85Parser.EvaluateThroughContext;
import io.proleap.cobol.Cobol85Parser.EvaluateValueContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface Condition extends CobolDivisionElement {

	enum Type {
		Any, Boolean, Condition, Value, ValueThrough
	}

	Through addThrough(EvaluateThroughContext ctx);

	Value addValue(EvaluateValueContext ctx);

	ValueStmt getConditionValueStmt();

	Through getThrough();

	Type getType();

	Value getValue();

	boolean isNot();

	void setConditionValueStmt(ValueStmt conditionValueStmt);

	void setNot(boolean not);

	void setType(Type type);
}
