/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface ByReference extends CobolDivisionElement {

	enum ByReferenceType {
		ADDRESS_OF, INTEGER, OMITTED, STRING
	}

	ByReferenceType getByReferenceType();

	ValueStmt getValueStmt();

	void setByReferenceType(ByReferenceType byReferenceType);

	void setValueStmt(ValueStmt valueStmt);
}
