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

	enum ByType {
		ALPHABETIC, ALPHANUMERIC, ALPHANUMERIC_EDITED, DBCS, EGCS, NATIONAL, NATIONAL_EDITED, NUMERIC, NUMERIC_EDITED
	}

	ByType getByType();

	ValueStmt getValueStmt();

	void setByType(ByType byType);

	void setValueStmt(ValueStmt valueStmt);
}
