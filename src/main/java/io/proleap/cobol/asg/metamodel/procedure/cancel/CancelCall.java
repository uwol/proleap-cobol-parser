/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.cancel;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface CancelCall extends CobolDivisionElement {

	enum CancelType {
		BY_FUNCTION, BY_TITLE
	}

	CancelType getCancelType();

	ValueStmt getValueStmt();

	void setCancelType(CancelType cancelType);

	void setValueStmt(ValueStmt valueStmt);
}
