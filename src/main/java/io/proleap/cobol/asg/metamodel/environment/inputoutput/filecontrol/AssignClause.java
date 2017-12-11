/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface AssignClause extends CobolDivisionElement {

	enum AssignClauseType {
		CALL, DISK, DISPLAY, KEYBOARD, PORT, PRINTER, READER, REMOTE, TAPE, VIRTUAL
	}

	AssignClauseType getAssignClauseType();

	ValueStmt getToValueStmt();

	void setAssignClauseType(AssignClauseType assignClauseType);

	void setToValueStmt(ValueStmt toValueStmt);

}
