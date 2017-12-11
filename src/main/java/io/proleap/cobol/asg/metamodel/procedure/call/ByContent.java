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

public interface ByContent extends CobolDivisionElement {

	enum ByContentType {
		ADDRESS_OF, LENGTH_OF
	}

	ByContentType getByContentType();

	ValueStmt getValueStmt();

	void setByContentType(ByContentType byContentType);

	void setValueStmt(ValueStmt valueStmt);
}
