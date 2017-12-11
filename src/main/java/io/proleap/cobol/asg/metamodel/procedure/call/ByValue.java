/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
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
