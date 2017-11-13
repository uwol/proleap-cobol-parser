/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.call;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface ByValue extends CobolDivisionElement {

	enum ByValueType {
		ADDRESS_OF, LENGTH_OF
	}

	ByValueType getByValueType();

	ValueStmt getValueStmt();

	void setByValueType(ByValueType byValueType);

	void setValueStmt(ValueStmt balueStmt);
}
