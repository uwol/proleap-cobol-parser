/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface AssignClause extends CobolDivisionElement {

	enum AssignClauseType {
		CALL, DISK, PORT, PRINTER, READER, REMOTE, TAPE, VIRTUAL
	}

	AssignClauseType getAssignClauseType();

	ValueStmt getToValueStmt();

	void setAssignClauseType(AssignClauseType assignClauseType);

	void setToValueStmt(ValueStmt toValueStmt);

}
