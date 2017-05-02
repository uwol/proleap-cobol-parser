/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.initialize;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface By extends CobolDivisionElement {

	enum Type {
		ALPHABETIC, ALPHANUMERIC, ALPHANUMERIC_EDITED, DBCS, EGCS, NATIONAL, NUMERIC, NUMERIC_EDITED
	}

	Type getType();

	ValueStmt getValueStmt();

	void setType(Type type);

	void setValueStmt(ValueStmt valueStmt);
}
